package com.learning.supplier_service;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.service.warehouse.WarehouseService;
import com.learning.supplier_service.core.usecase.offer.CreateOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.CreateOfferUsecaseRequest;
import com.learning.supplier_service.core.usecase.offer.UpdateOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.UpdateOfferUsecaseRequest;
import com.learning.supplier_service.core.usecase.supplier.CreateSupplierUseCase;
import com.learning.supplier_service.core.usecase.supplier.CreateSupplierUsecaseRequest;
import com.learning.supplier_service.core.usecase.supplier.SubmitSupplierKycUseCase;
import com.learning.supplier_service.core.usecase.supplier.SubmitSupplierKycUsecaseRequest;
import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUseCase;
import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUsecaseRequest;
import com.learning.supplier_service.exception.DuplicateOfferException;
import com.learning.supplier_service.gateway.SupplierKycRepository;
import com.learning.supplier_service.gateway.SupplierOutboxRepository;
import com.learning.supplier_service.gateway.SupplierProductRepository;
import com.learning.supplier_service.gateway.SupplierRepository;
import com.learning.supplier_service.gateway.SupplierWarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class SupplierFlowIntegrationTest {
    @Autowired
    private CreateSupplierUseCase createSupplierUseCase;

    @Autowired
    private SubmitSupplierKycUseCase submitSupplierKycUseCase;

    @Autowired
    private CreateWarehouseUseCase createWarehouseUseCase;

    @Autowired
    private CreateOfferUseCase createOfferUseCase;

    @Autowired
    private UpdateOfferUseCase updateOfferUseCase;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private SupplierProductRepository productRepository;

    @Autowired
    private SupplierWarehouseRepository warehouseRepository;

    @Autowired
    private SupplierKycRepository kycRepository;

    @Autowired
    private SupplierOutboxRepository outboxRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @BeforeEach
    void cleanDatabase() {
        productRepository.deleteAll();
        warehouseRepository.deleteAll();
        kycRepository.deleteAll();
        outboxRepository.deleteAll();
        supplierRepository.deleteAll();
    }

    @Test
    void supplierLifecyclePersistsStateAndOutboxEvents() {
        var supplier = createSupplierUseCase.execute(new CreateSupplierUsecaseRequest(
                "Acme Retail Private Limited",
                "Acme Retail",
                "owner@acme.test",
                "+91-9999999999",
                "GSTIN-123",
                "in",
                new BigDecimal("10.00")
        ));
        var productId = UUID.randomUUID();

        var kyc = submitSupplierKycUseCase.execute(new SubmitSupplierKycUsecaseRequest(
                supplier.supplierId(),
                "GST_CERTIFICATE",
                "s3://supplier-docs/gstin.pdf"
        ));
        var warehouse = createWarehouseUseCase.execute(new CreateWarehouseUsecaseRequest(
                supplier.supplierId(),
                "Bengaluru Main",
                "blr-01",
                "100 Market Road",
                null,
                "Bengaluru",
                "Karnataka",
                "560001",
                "in",
                new BigDecimal("12.971599"),
                new BigDecimal("77.594566")
        ));
        var offer = createOfferUseCase.execute(new CreateOfferUsecaseRequest(
                supplier.supplierId(),
                productId,
                "ACME-IPHONE-15",
                new BigDecimal("79999.00"),
                "inr",
                OfferCondition.NEW,
                (short) 1
        ));
        var updated = updateOfferUseCase.execute(new UpdateOfferUsecaseRequest(
                supplier.supplierId(),
                offer.offerId(),
                new BigDecimal("78999.00"),
                (short) 2,
                false
        ));

        assertEquals(OnboardingStatus.KYC_SUBMITTED, kyc.onboardingStatus());
        assertEquals("BLR-01", warehouse.code());
        assertEquals(1, warehouseService.list(supplier.supplierId()).size());
        assertEquals(new BigDecimal("78999.00"), updated.price());
        assertTrue(offerService.listActiveByProduct(productId).isEmpty());

        var eventTypes = outboxRepository.findAllByOrderByIdAsc().stream()
                .map(event -> event.getEventType())
                .toList();
        assertEquals(
                java.util.List.of(
                        "SUPPLIER_CREATED",
                        "SUPPLIER_KYC_SUBMITTED",
                        "SUPPLIER_WAREHOUSE_DECLARED",
                        "OFFER_CREATED",
                        "OFFER_PRICE_UPDATED",
                        "OFFER_DEACTIVATED"
                ),
                eventTypes
        );
    }

    @Test
    void duplicateOfferForSameSupplierAndProductIsRejected() {
        var supplier = createSupplierUseCase.execute(new CreateSupplierUsecaseRequest(
                "Acme Retail Private Limited",
                "Acme Retail",
                "owner@acme.test",
                null,
                null,
                "IN",
                new BigDecimal("10.00")
        ));
        var productId = UUID.randomUUID();
        var request = new CreateOfferUsecaseRequest(
                supplier.supplierId(),
                productId,
                "ACME-IPHONE-15",
                new BigDecimal("79999.00"),
                "INR",
                OfferCondition.NEW,
                (short) 1
        );

        createOfferUseCase.execute(request);

        assertThrows(DuplicateOfferException.class, () -> createOfferUseCase.execute(new CreateOfferUsecaseRequest(
                supplier.supplierId(),
                productId,
                "ACME-IPHONE-15-B",
                new BigDecimal("79999.00"),
                "INR",
                OfferCondition.NEW,
                (short) 1
        )));
    }
}
