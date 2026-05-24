package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;

public record GetOfferUsecaseResponse(SupplierProductEntity offer) implements UsecaseResponse {
}

