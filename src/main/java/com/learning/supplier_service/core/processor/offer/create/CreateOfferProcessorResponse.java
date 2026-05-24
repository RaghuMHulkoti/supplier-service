package com.learning.supplier_service.core.processor.offer.create;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;

public record CreateOfferProcessorResponse(SupplierProductEntity offer) implements ProcessorResponse {
}
