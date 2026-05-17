package com.learning.supplier_service.core.service.supplier;

import com.learning.supplier_service.core.service.ServiceRequest;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierServiceRequest implements ServiceRequest {

    private String legalName;
    private String displayName;
    private String email;
    private String phone;
    private String taxId;
    private String country;
    private BigDecimal commissionPct;
}