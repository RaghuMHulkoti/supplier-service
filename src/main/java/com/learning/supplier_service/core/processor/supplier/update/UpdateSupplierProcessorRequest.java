package com.learning.supplier_service.core.processor.supplier.update;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private String legalName;
    private String displayName;
    private String phone;
    private String taxId;
    private BigDecimal commissionPct;
}
