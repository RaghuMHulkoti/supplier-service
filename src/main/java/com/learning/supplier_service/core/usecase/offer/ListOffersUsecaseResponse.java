package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;
import org.springframework.data.domain.Page;

public record ListOffersUsecaseResponse(Page<SupplierProductEntity> offers) implements UsecaseResponse {
}

