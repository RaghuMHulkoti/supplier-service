package com.learning.supplier_service.core.service.offer.create;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.core.service.ServiceRequest;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferServiceRequest implements ServiceRequest {
    private SupplierEntity supplier;
    private UUID productId;
    private String supplierSku;
    private BigDecimal price;
    private String currency;
    private OfferCondition condition;
    private Short handlingDays;
}
