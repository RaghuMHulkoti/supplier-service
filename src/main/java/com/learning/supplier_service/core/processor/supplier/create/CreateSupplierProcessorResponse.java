package com.learning.supplier_service.core.processor.supplier.create;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierProcessorResponse implements ProcessorResponse {

    private UUID supplierId;
    private boolean success;
}