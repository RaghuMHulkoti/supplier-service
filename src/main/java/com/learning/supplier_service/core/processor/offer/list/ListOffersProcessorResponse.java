package com.learning.supplier_service.core.processor.offer.list;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;
import org.springframework.data.domain.Page;

public record ListOffersProcessorResponse(Page<SupplierProductEntity> offers) implements ProcessorResponse {
}
