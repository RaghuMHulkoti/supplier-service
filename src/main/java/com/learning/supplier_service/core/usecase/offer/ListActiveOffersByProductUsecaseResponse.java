package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierProductEntity;

import java.util.List;

public record ListActiveOffersByProductUsecaseResponse(List<SupplierProductEntity> offers) implements UsecaseResponse {
}

