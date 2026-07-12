package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.processor.supplier.kyc.get.GetKycProcessor;
import com.learning.supplier_service.core.processor.supplier.kyc.get.GetKycProcessorRequest;
import com.learning.supplier_service.core.processor.supplier.kyc.submit.SubmitKycProcessor;
import com.learning.supplier_service.core.processor.supplier.kyc.submit.SubmitKycProcessorRequest;
import com.learning.supplier_service.web.model.SubmitSupplierKycRequest;
import com.learning.supplier_service.web.response.KycDetailResponse;
import com.learning.supplier_service.web.response.SupplierKycResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/kyc")
public class KycController {

    private final SubmitKycProcessor submitKycProcessor;
    private final GetKycProcessor getKycProcessor;

    public KycController(SubmitKycProcessor submitKycProcessor, GetKycProcessor getKycProcessor) {
        this.submitKycProcessor = submitKycProcessor;
        this.getKycProcessor = getKycProcessor;
    }

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
}
