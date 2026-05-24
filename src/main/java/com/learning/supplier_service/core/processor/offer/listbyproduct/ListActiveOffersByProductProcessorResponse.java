package com.learning.supplier_service.core.processor.offer.listbyproduct;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;

import java.util.List;

public record ListActiveOffersByProductProcessorResponse(List<SupplierProductEntity> offers) implements ProcessorResponse {
}
