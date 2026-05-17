package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.service.supplier.SupplierService;
import com.learning.supplier_service.core.service.warehouse.WarehouseService;
import com.learning.supplier_service.core.usecase.offer.CreateOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.CreateOfferUsecaseRequest;
import com.learning.supplier_service.core.usecase.offer.UpdateOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.UpdateOfferUsecaseRequest;
import com.learning.supplier_service.core.usecase.supplier.*;
import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUseCase;
import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUsecaseRequest;
import com.learning.supplier_service.web.model.CreateOfferRequest;
import com.learning.supplier_service.web.model.CreateSupplierRequest;
import com.learning.supplier_service.web.model.CreateWarehouseRequest;
import com.learning.supplier_service.web.model.SubmitSupplierKycRequest;
import com.learning.supplier_service.web.model.UpdateOfferRequest;
import com.learning.supplier_service.web.response.OfferResponse;
import com.learning.supplier_service.web.response.PageResponse;
import com.learning.supplier_service.web.response.SupplierKycResponse;
import com.learning.supplier_service.web.response.SupplierResponse;
import com.learning.supplier_service.web.response.WarehouseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    private static final BigDecimal DEFAULT_COMMISSION_PCT = new BigDecimal("10.00");

    private final CreateSupplierUseCase createSupplierUseCase;
    private final SubmitSupplierKycUseCase submitSupplierKycUseCase;
    private final CreateWarehouseUseCase createWarehouseUseCase;
    private final CreateOfferUseCase createOfferUseCase;
    private final UpdateOfferUseCase updateOfferUseCase;
    private final SupplierService supplierService;
    private final WarehouseService warehouseService;
    private final OfferService offerService;

    public SupplierController(
            CreateSupplierUseCase createSupplierUseCase,
            SubmitSupplierKycUseCase submitSupplierKycUseCase,
            CreateWarehouseUseCase createWarehouseUseCase,
            CreateOfferUseCase createOfferUseCase,
            UpdateOfferUseCase updateOfferUseCase,
            SupplierService supplierService,
            WarehouseService warehouseService,
            OfferService offerService
    ) {
        this.createSupplierUseCase = createSupplierUseCase;
        this.submitSupplierKycUseCase = submitSupplierKycUseCase;
        this.createWarehouseUseCase = createWarehouseUseCase;
        this.createOfferUseCase = createOfferUseCase;
        this.updateOfferUseCase = updateOfferUseCase;
        this.supplierService = supplierService;
        this.warehouseService = warehouseService;
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@Valid @RequestBody CreateSupplierRequest request) {
        var response = createSupplierUseCase.execute(buildUsecaseRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(request, response));
    }

    private static SupplierResponse buildResponse(CreateSupplierRequest request, CreateSupplierUsecaseResponse response) {
        return new SupplierResponse(
                response.supplierId(),
                response.legalName(),
                response.displayName(),
                response.email(),
                request.phone(),
                request.taxId(),
                request.country().toUpperCase(Locale.ROOT),
                response.onboardingStatus(),
                BigDecimal.ZERO,
                response.commissionPct(),
                response.createdAt(),
                response.createdAt()
        );
    }

    private static CreateSupplierUsecaseRequest buildUsecaseRequest(CreateSupplierRequest request) {
        return new CreateSupplierUsecaseRequest(
                request.legalName(),
                request.displayName(),
                request.email(),
                request.phone(),
                request.taxId(),
                request.country(),
                request.commissionPct() == null ? DEFAULT_COMMISSION_PCT : request.commissionPct()
        );
    }

    @GetMapping("/{supplierId}")
    public SupplierResponse getSupplier(@PathVariable UUID supplierId) {
        return SupplierResponse.from(supplierService.getRequired(supplierId));
    }

    @PostMapping("/{supplierId}/kyc")
    public SupplierKycResponse submitKyc(
            @PathVariable UUID supplierId,
            @Valid @RequestBody SubmitSupplierKycRequest request
    ) {
        var response = submitSupplierKycUseCase.execute(new SubmitSupplierKycUsecaseRequest(
                supplierId,
                request.documentKind(),
                request.documentUrl()
        ));

        return new SupplierKycResponse(
                response.supplierId(),
                response.documentKind(),
                response.documentUrl(),
                response.onboardingStatus()
        );
    }

    @PostMapping("/{supplierId}/warehouses")
    public ResponseEntity<WarehouseResponse> createWarehouse(
            @PathVariable UUID supplierId,
            @Valid @RequestBody CreateWarehouseRequest request
    ) {
        var response = createWarehouseUseCase.execute(new CreateWarehouseUsecaseRequest(
                supplierId,
                request.name(),
                request.code(),
                request.addressLine1(),
                request.addressLine2(),
                request.city(),
                request.state(),
                request.postalCode(),
                request.country(),
                request.geoLat(),
                request.geoLng()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(new WarehouseResponse(
                response.warehouseId(),
                response.supplierId(),
                request.name(),
                response.code(),
                request.addressLine1(),
                request.addressLine2(),
                request.city(),
                request.state(),
                request.postalCode(),
                request.country().toUpperCase(Locale.ROOT),
                request.geoLat(),
                request.geoLng(),
                response.active(),
                response.createdAt()
        ));
    }

    @GetMapping("/{supplierId}/warehouses")
    public List<WarehouseResponse> listWarehouses(@PathVariable UUID supplierId) {
        supplierService.getRequired(supplierId);
        return warehouseService.list(supplierId).stream()
                .map(WarehouseResponse::from)
                .toList();
    }

    @PostMapping("/{supplierId}/offers")
    public ResponseEntity<OfferResponse> createOffer(
            @PathVariable UUID supplierId,
            @Valid @RequestBody CreateOfferRequest request
    ) {
        var response = createOfferUseCase.execute(new CreateOfferUsecaseRequest(
                supplierId,
                request.productId(),
                request.supplierSku(),
                request.price(),
                request.currency(),
                request.condition(),
                request.handlingDays()
        ));
        var offer = offerService.getRequired(response.offerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(OfferResponse.from(offer));
    }

    @PatchMapping("/{supplierId}/offers/{offerId}")
    public OfferResponse updateOffer(
            @PathVariable UUID supplierId,
            @PathVariable UUID offerId,
            @Valid @RequestBody UpdateOfferRequest request
    ) {
        updateOfferUseCase.execute(new UpdateOfferUsecaseRequest(
                supplierId,
                offerId,
                request.price(),
                request.handlingDays(),
                request.active()
        ));
        return OfferResponse.from(offerService.getRequired(supplierId, offerId));
    }

    @GetMapping("/{supplierId}/offers")
    public PageResponse<OfferResponse> listOffers(
            @PathVariable UUID supplierId,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size
    ) {
        supplierService.getRequired(supplierId);
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return PageResponse.from(offerService.list(supplierId, pageable), OfferResponse::from);
    }
}
