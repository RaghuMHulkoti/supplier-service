package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.processor.supplier.create.CreateSupplierProcessor;
import com.learning.supplier_service.core.processor.supplier.create.CreateSupplierProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.get.FetchSupplierProcessor;
import com.learning.supplier_service.core.processor.supplier.get.GetSupplierProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.update.UpdateSupplierProcessor;
import com.learning.supplier_service.core.processor.supplier.update.UpdateSupplierProcessorRequest;
import com.learning.supplier_service.exception.SupplierException;
import com.learning.supplier_service.web.mapper.SupplierMapper;
import com.learning.supplier_service.web.model.SupplierWebRequest;
import com.learning.supplier_service.web.model.UpdateSupplierRequest;
import com.learning.supplier_service.web.response.PageResponse;
import com.learning.supplier_service.web.response.Response;
import com.learning.supplier_service.web.response.SupplierResponse;
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
import java.util.UUID;
import static java.util.Optional.of;

@Validated
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private static final BigDecimal DEFAULT_COMMISSION_PCT = new BigDecimal("10.00");

    private final CreateSupplierProcessor createSupplierProcessor;
    private final FetchSupplierProcessor fetchSupplierProcessor;
    private final UpdateSupplierProcessor updateSupplierProcessor;


    public SupplierController(
            CreateSupplierProcessor createSupplierProcessor,
            FetchSupplierProcessor fetchSupplierProcessor,
            UpdateSupplierProcessor updateSupplierProcessor
    ) {
        this.createSupplierProcessor = createSupplierProcessor;
        this.fetchSupplierProcessor = fetchSupplierProcessor;
        this.updateSupplierProcessor = updateSupplierProcessor;
    }


    @PostMapping
    public ResponseEntity<Response<Object>> createSupplier(@Valid @RequestBody SupplierWebRequest request) {
        return of(request)
                .map(this::buildCreateSupplierRequest)
                .map(createSupplierProcessor::process)
                .map(SupplierMapper::mapSupplierCreateResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new SupplierException("Unable to create Supplier"));

    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Response<Object>> getSupplier(@PathVariable UUID supplierId) {
        return of(fetchSupplierProcessor.process(GetSupplierProcessorRequest.forSingleSupplier(supplierId)))
                .map(SupplierMapper::mapSupplierGetResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new SupplierException("Unable to fetch Supplier"));
//        var response = fetchSupplierProcessor.process(GetSupplierProcessorRequest.forSingleSupplier(supplierId));
//        return ResponseEntity.ok(SupplierResponse.from(response.getSupplier()));
    }

    private static GetSupplierProcessorRequest getSupplierProcessorRequest(UUID supplierId) {
        return GetSupplierProcessorRequest.forSingleSupplier(supplierId);
    }

//    @GetMapping
//    public ResponseEntity<PageResponse<SupplierResponse>> listSuppliers(
//            @RequestParam(defaultValue = "0") @Min(0) int page,
//            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size
//    ) {
//        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
//        var response = fetchSupplierProcessor.process(GetSupplierProcessorRequest.forList(pageable));
//        return ResponseEntity.ok(PageResponse.from(response.getSuppliers(), SupplierResponse::from));
//    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @Valid @RequestBody UpdateSupplierRequest request
    ) {
        var response = updateSupplierProcessor.process(UpdateSupplierProcessorRequest.builder()
                .supplierId(request.supplierId()).legalName(request.legalName()).displayName(request.displayName())
                .phone(request.phone()).taxId(request.taxId()).commissionPct(request.commissionPct())
                .newStatus(request.status())
                .build());
        return ResponseEntity.ok(SupplierResponse.from(response.getSupplier()));
    }


    private CreateSupplierProcessorRequest buildCreateSupplierRequest(SupplierWebRequest request) {
        return CreateSupplierProcessorRequest.builder()
                .legalName(request.legalName()).displayName(request.displayName())
                .email(request.email()).phone(request.phone()).taxId(request.taxId())
                .country(request.country())
                .commissionPct(request.commissionPct() == null ? DEFAULT_COMMISSION_PCT : request.commissionPct())
                .build();
    }


}
