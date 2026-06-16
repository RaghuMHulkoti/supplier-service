package com.learning.supplier_service.core.service.supplier.update;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.gateway.SupplierGateway;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateSupplierServiceTest {

    @Mock
    private SupplierGateway supplierGateway;

    @Mock
    private OutboxService outboxService;

    private UpdateSupplierService updateSupplierService;

    @BeforeEach
    void setUp() {
        updateSupplierService = new UpdateSupplierService(supplierGateway, outboxService);
    }

    @Test
    void execute_updatesFieldsAndStatusWhenBothAreProvided() {
        var supplierId = UUID.randomUUID();
        var supplier = new SupplierEntity(
                supplierId,
                "Old Legal Name",
                "Old Display Name",
                "supplier@example.com",
                "+91-1111111111",
                "GST-OLD",
                "IN",
                OnboardingStatus.PENDING,
                new BigDecimal("4.50"),
                new BigDecimal("10.00")
        );

        when(supplierGateway.getSupplierById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierGateway.saveSupplier(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var response = updateSupplierService.execute(UpdateSupplierServiceRequest.builder()
                .supplierId(supplierId)
                .legalName("New Legal Name")
                .displayName("New Display Name")
                .phone("+91-2222222222")
                .taxId("GST-NEW")
                .commissionPct(new BigDecimal("12.50"))
                .newStatus(OnboardingStatus.APPROVED)
                .build());

        assertSame(supplier, response.supplier());
        assertEquals("New Legal Name", supplier.getLegalName());
        assertEquals("New Display Name", supplier.getDisplayName());
        assertEquals("+91-2222222222", supplier.getPhone());
        assertEquals("GST-NEW", supplier.getTaxId());
        assertEquals(new BigDecimal("12.50"), supplier.getCommissionPct());
        assertEquals(OnboardingStatus.APPROVED, supplier.getOnboardingStatus());

        verify(supplierGateway).saveSupplier(supplier);
        verify(outboxService).append(eq(supplierId), eq("SUPPLIER_STATUS_UPDATED"), any());
    }

    @Test
    void execute_updatesStatusOnlyWhenOnlyStatusIsProvided() {
        var supplierId = UUID.randomUUID();
        var supplier = new SupplierEntity(
                supplierId,
                "Old Legal Name",
                "Old Display Name",
                "supplier@example.com",
                "+91-1111111111",
                "GST-OLD",
                "IN",
                OnboardingStatus.PENDING,
                new BigDecimal("4.50"),
                new BigDecimal("10.00")
        );

        when(supplierGateway.getSupplierById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierGateway.saveSupplier(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var response = updateSupplierService.execute(UpdateSupplierServiceRequest.builder()
                .supplierId(supplierId)
                .newStatus(OnboardingStatus.KYC_SUBMITTED)
                .build());

        assertSame(supplier, response.supplier());
        assertEquals("Old Legal Name", supplier.getLegalName());
        assertEquals("Old Display Name", supplier.getDisplayName());
        assertEquals(OnboardingStatus.KYC_SUBMITTED, supplier.getOnboardingStatus());

        verify(supplierGateway).saveSupplier(supplier);
        verify(outboxService).append(eq(supplierId), eq("SUPPLIER_STATUS_UPDATED"), any());
    }
}


