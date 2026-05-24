package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.processor.offer.create.CreateOfferProcessor;
import com.learning.supplier_service.core.processor.offer.create.CreateOfferProcessorRequest;
import com.learning.supplier_service.core.processor.offer.get.GetOfferProcessor;
import com.learning.supplier_service.core.processor.offer.get.GetOfferProcessorRequest;
import com.learning.supplier_service.core.processor.offer.list.ListOffersProcessor;
import com.learning.supplier_service.core.processor.offer.list.ListOffersProcessorRequest;
import com.learning.supplier_service.core.processor.offer.update.UpdateOfferProcessor;
import com.learning.supplier_service.core.processor.offer.update.UpdateOfferProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.create.CreateSupplierProcessor;
import com.learning.supplier_service.core.processor.supplier.create.CreateSupplierProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.get.GetSupplierProcessor;
import com.learning.supplier_service.core.processor.supplier.get.GetSupplierProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.kyc.get.GetKycProcessor;
import com.learning.supplier_service.core.processor.supplier.kyc.get.GetKycProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.kyc.submit.SubmitKycProcessor;
import com.learning.supplier_service.core.processor.supplier.kyc.submit.SubmitKycProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.list.ListSuppliersProcessor;
import com.learning.supplier_service.core.processor.supplier.list.ListSuppliersProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.status.UpdateSupplierStatusProcessor;
import com.learning.supplier_service.core.processor.supplier.status.UpdateSupplierStatusProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.update.UpdateSupplierProcessor;
import com.learning.supplier_service.core.processor.supplier.update.UpdateSupplierProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.create.CreateWareHouseProcessor;
import com.learning.supplier_service.core.processor.warehouse.create.CreateWareHouseProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.get.GetWarehouseProcessor;
import com.learning.supplier_service.core.processor.warehouse.get.GetWarehouseProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.list.ListWarehousesProcessor;
import com.learning.supplier_service.core.processor.warehouse.list.ListWarehousesProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.status.ToggleWarehouseStatusProcessor;
import com.learning.supplier_service.core.processor.warehouse.status.ToggleWarehouseStatusProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.update.UpdateWarehouseProcessor;
import com.learning.supplier_service.core.processor.warehouse.update.UpdateWarehouseProcessorRequest;
import com.learning.supplier_service.web.model.CreateOfferRequest;
import com.learning.supplier_service.web.model.CreateWarehouseRequest;
import com.learning.supplier_service.web.model.SubmitSupplierKycRequest;
import com.learning.supplier_service.web.model.SupplierWebRequest;
import com.learning.supplier_service.web.model.ToggleWarehouseStatusRequest;
import com.learning.supplier_service.web.model.UpdateOfferRequest;
import com.learning.supplier_service.web.model.UpdateSupplierRequest;
import com.learning.supplier_service.web.model.UpdateSupplierStatusRequest;
import com.learning.supplier_service.web.model.UpdateWarehouseRequest;
import com.learning.supplier_service.web.response.KycDetailResponse;
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
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private static final BigDecimal DEFAULT_COMMISSION_PCT = new BigDecimal("10.00");

    private final CreateSupplierProcessor createSupplierProcessor;
    private final GetSupplierProcessor getSupplierProcessor;
    private final ListSuppliersProcessor listSuppliersProcessor;
    private final UpdateSupplierProcessor updateSupplierProcessor;
    private final UpdateSupplierStatusProcessor updateSupplierStatusProcessor;
    private final SubmitKycProcessor submitKycProcessor;
    private final GetKycProcessor getKycProcessor;
    private final CreateWareHouseProcessor createWareHouseProcessor;
    private final ListWarehousesProcessor listWarehousesProcessor;
    private final GetWarehouseProcessor getWarehouseProcessor;
    private final UpdateWarehouseProcessor updateWarehouseProcessor;
    private final ToggleWarehouseStatusProcessor toggleWarehouseStatusProcessor;
    private final CreateOfferProcessor createOfferProcessor;
    private final GetOfferProcessor getOfferProcessor;
    private final UpdateOfferProcessor updateOfferProcessor;
    private final ListOffersProcessor listOffersProcessor;

    public SupplierController(
            CreateSupplierProcessor createSupplierProcessor,
            GetSupplierProcessor getSupplierProcessor,
            ListSuppliersProcessor listSuppliersProcessor,
            UpdateSupplierProcessor updateSupplierProcessor,
            UpdateSupplierStatusProcessor updateSupplierStatusProcessor,
            SubmitKycProcessor submitKycProcessor,
            GetKycProcessor getKycProcessor,
            CreateWareHouseProcessor createWareHouseProcessor,
            ListWarehousesProcessor listWarehousesProcessor,
            GetWarehouseProcessor getWarehouseProcessor,
            UpdateWarehouseProcessor updateWarehouseProcessor,
            ToggleWarehouseStatusProcessor toggleWarehouseStatusProcessor,
            CreateOfferProcessor createOfferProcessor,
            GetOfferProcessor getOfferProcessor,
            UpdateOfferProcessor updateOfferProcessor,
            ListOffersProcessor listOffersProcessor
    ) {
        this.createSupplierProcessor = createSupplierProcessor;
        this.getSupplierProcessor = getSupplierProcessor;
        this.listSuppliersProcessor = listSuppliersProcessor;
        this.updateSupplierProcessor = updateSupplierProcessor;
        this.updateSupplierStatusProcessor = updateSupplierStatusProcessor;
        this.submitKycProcessor = submitKycProcessor;
        this.getKycProcessor = getKycProcessor;
        this.createWareHouseProcessor = createWareHouseProcessor;
        this.listWarehousesProcessor = listWarehousesProcessor;
        this.getWarehouseProcessor = getWarehouseProcessor;
        this.updateWarehouseProcessor = updateWarehouseProcessor;
        this.toggleWarehouseStatusProcessor = toggleWarehouseStatusProcessor;
        this.createOfferProcessor = createOfferProcessor;
        this.getOfferProcessor = getOfferProcessor;
        this.updateOfferProcessor = updateOfferProcessor;
        this.listOffersProcessor = listOffersProcessor;
    }

    // =================== SUPPLIER ===================

    @PostMapping
    public ResponseEntity<Object> createSupplier(@Valid @RequestBody SupplierWebRequest request) {
        var response = createSupplierProcessor.process(buildCreateSupplierRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable UUID supplierId) {
        var response = getSupplierProcessor.process(new GetSupplierProcessorRequest(supplierId));
        return ResponseEntity.ok(SupplierResponse.from(response.supplier()));
    }

    @GetMapping
    public ResponseEntity<PageResponse<SupplierResponse>> listSuppliers(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size
    ) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        var response = listSuppliersProcessor.process(new ListSuppliersProcessorRequest(pageable));
        return ResponseEntity.ok(PageResponse.from(response.suppliers(), SupplierResponse::from));
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable UUID supplierId,
            @Valid @RequestBody UpdateSupplierRequest request
    ) {
        var response = updateSupplierProcessor.process(UpdateSupplierProcessorRequest.builder()
                .supplierId(supplierId).legalName(request.legalName()).displayName(request.displayName())
                .phone(request.phone()).taxId(request.taxId()).commissionPct(request.commissionPct()).build());
        return ResponseEntity.ok(SupplierResponse.from(response.supplier()));
    }

    @PatchMapping("/{supplierId}/status")
    public ResponseEntity<Object> updateSupplierStatus(
            @PathVariable UUID supplierId,
            @Valid @RequestBody UpdateSupplierStatusRequest request
    ) {
        var response = updateSupplierStatusProcessor.process(
                UpdateSupplierStatusProcessorRequest.builder()
                        .supplierId(supplierId).newStatus(request.status()).build());
        return ResponseEntity.ok(response);
    }

    // =================== KYC ===================

    @PostMapping("/{supplierId}/kyc")
    public ResponseEntity<SupplierKycResponse> submitKyc(
            @PathVariable UUID supplierId,
            @Valid @RequestBody SubmitSupplierKycRequest request
    ) {
        var response = submitKycProcessor.process(SubmitKycProcessorRequest.builder()
                .supplierId(supplierId).documentKind(request.documentKind()).documentUrl(request.documentUrl()).build());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SupplierKycResponse(response.supplierId(), response.documentKind(),
                        response.documentUrl(), response.onboardingStatus()));
    }

    @GetMapping("/{supplierId}/kyc")
    public ResponseEntity<KycDetailResponse> getKyc(@PathVariable UUID supplierId) {
        var response = getKycProcessor.process(new GetKycProcessorRequest(supplierId));
        return ResponseEntity.ok(KycDetailResponse.from(response.kyc()));
    }

    // =================== WAREHOUSE ===================

    @PostMapping("/{supplierId}/warehouses")
    public ResponseEntity<Object> createWarehouse(
            @PathVariable UUID supplierId, @Valid @RequestBody CreateWarehouseRequest request) {
        var response = createWareHouseProcessor.process(buildCreateWarehouseRequest(supplierId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{supplierId}/warehouses")
    public ResponseEntity<List<WarehouseResponse>> listWarehouses(@PathVariable UUID supplierId) {
        var response = listWarehousesProcessor.process(new ListWarehousesProcessorRequest(supplierId));
        return ResponseEntity.ok(response.warehouses().stream().map(WarehouseResponse::from).toList());
    }

    @GetMapping("/{supplierId}/warehouses/{warehouseId}")
    public ResponseEntity<WarehouseResponse> getWarehouse(
            @PathVariable UUID supplierId, @PathVariable UUID warehouseId) {
        var response = getWarehouseProcessor.process(new GetWarehouseProcessorRequest(supplierId, warehouseId));
        return ResponseEntity.ok(WarehouseResponse.from(response.warehouse()));
    }

    @PatchMapping("/{supplierId}/warehouses/{warehouseId}")
    public ResponseEntity<WarehouseResponse> updateWarehouse(
            @PathVariable UUID supplierId, @PathVariable UUID warehouseId,
            @Valid @RequestBody UpdateWarehouseRequest request) {
        var response = updateWarehouseProcessor.process(UpdateWarehouseProcessorRequest.builder()
                .supplierId(supplierId).warehouseId(warehouseId).name(request.name())
                .addressLine1(request.addressLine1()).addressLine2(request.addressLine2())
                .city(request.city()).state(request.state()).postalCode(request.postalCode())
                .country(request.country()).geoLat(request.geoLat()).geoLng(request.geoLng()).build());
        return ResponseEntity.ok(WarehouseResponse.from(response.warehouse()));
    }

    @PatchMapping("/{supplierId}/warehouses/{warehouseId}/status")
    public ResponseEntity<Object> toggleWarehouseStatus(
            @PathVariable UUID supplierId, @PathVariable UUID warehouseId,
            @Valid @RequestBody ToggleWarehouseStatusRequest request) {
        var response = toggleWarehouseStatusProcessor.process(
                ToggleWarehouseStatusProcessorRequest.builder()
                        .supplierId(supplierId).warehouseId(warehouseId).active(request.active()).build());
        return ResponseEntity.ok(response);
    }

    // =================== OFFERS ===================

    @PostMapping("/{supplierId}/offers")
    public ResponseEntity<OfferResponse> createOffer(
            @PathVariable UUID supplierId, @Valid @RequestBody CreateOfferRequest request) {
        var response = createOfferProcessor.process(CreateOfferProcessorRequest.builder()
                .supplierId(supplierId).productId(request.productId()).supplierSku(request.supplierSku())
                .price(request.price()).currency(request.currency())
                .condition(request.condition()).handlingDays(request.handlingDays()).build());
        return ResponseEntity.status(HttpStatus.CREATED).body(OfferResponse.from(response.offer()));
    }

    @GetMapping("/{supplierId}/offers/{offerId}")
    public ResponseEntity<OfferResponse> getOffer(
            @PathVariable UUID supplierId, @PathVariable UUID offerId) {
        var response = getOfferProcessor.process(GetOfferProcessorRequest.bySupplierAndId(supplierId, offerId));
        return ResponseEntity.ok(OfferResponse.from(response.offer()));
    }

    @PatchMapping("/{supplierId}/offers/{offerId}")
    public ResponseEntity<OfferResponse> updateOffer(
            @PathVariable UUID supplierId, @PathVariable UUID offerId,
            @Valid @RequestBody UpdateOfferRequest request) {
        var response = updateOfferProcessor.process(UpdateOfferProcessorRequest.builder()
                .supplierId(supplierId).offerId(offerId).price(request.price())
                .handlingDays(request.handlingDays()).active(request.active()).build());
        return ResponseEntity.ok(OfferResponse.from(response.offer()));
    }

    @GetMapping("/{supplierId}/offers")
    public ResponseEntity<PageResponse<OfferResponse>> listOffers(
            @PathVariable UUID supplierId,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        var response = listOffersProcessor.process(new ListOffersProcessorRequest(supplierId, pageable));
        return ResponseEntity.ok(PageResponse.from(response.offers(), OfferResponse::from));
    }

    // =================== PRIVATE BUILDERS ===================

    private static CreateSupplierProcessorRequest buildCreateSupplierRequest(SupplierWebRequest request) {
        return CreateSupplierProcessorRequest.builder()
                .legalName(request.legalName()).displayName(request.displayName())
                .email(request.email()).phone(request.phone()).taxId(request.taxId())
                .country(request.country())
                .commissionPct(request.commissionPct() == null ? DEFAULT_COMMISSION_PCT : request.commissionPct())
                .build();
    }

    private static CreateWareHouseProcessorRequest buildCreateWarehouseRequest(UUID supplierId, CreateWarehouseRequest request) {
        return CreateWareHouseProcessorRequest.builder()
                .supplierId(supplierId).name(request.name()).code(request.code())
                .addressLine1(request.addressLine1()).addressLine2(request.addressLine2())
                .city(request.city()).state(request.state()).postalCode(request.postalCode())
                .country(request.country()).geoLat(request.geoLat()).geoLng(request.geoLng()).build();
    }
}
