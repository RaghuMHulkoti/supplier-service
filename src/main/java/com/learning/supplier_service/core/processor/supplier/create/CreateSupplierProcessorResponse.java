package com.learning.supplier_service.core.processor.supplier.create;

import com.learning.supplier_service.common.StatusCode;
import com.learning.supplier_service.core.processor.ProcessorResponse;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierProcessorResponse implements ProcessorResponse {

    private UUID supplierId;
    private StatusCode statusCode;;
}