package com.learning.supplier_service.core.usecase.supplier.create;

import com.learning.supplier_service.core.usecase.UsecaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierUsecaseRequest implements UsecaseRequest {

    private String legalName;
    private String displayName;
    private String email;
    private String phone;
    private String taxId;
    private String country;
    private BigDecimal commissionPct;
}