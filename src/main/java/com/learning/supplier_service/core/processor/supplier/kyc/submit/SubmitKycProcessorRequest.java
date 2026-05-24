package com.learning.supplier_service.core.processor.supplier.kyc.submit;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitKycProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private String documentKind;
    private String documentUrl;
}
