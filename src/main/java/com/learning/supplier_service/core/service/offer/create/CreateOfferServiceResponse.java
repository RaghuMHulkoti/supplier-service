package com.learning.supplier_service.core.service.offer.create;

import com.learning.supplier_service.core.service.ServiceResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferServiceResponse implements ServiceResponse {
    private SupplierProductEntity offer;
}
