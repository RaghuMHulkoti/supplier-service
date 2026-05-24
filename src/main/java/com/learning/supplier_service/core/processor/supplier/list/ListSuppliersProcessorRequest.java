package com.learning.supplier_service.core.processor.supplier.list;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import org.springframework.data.domain.Pageable;

public record ListSuppliersProcessorRequest(Pageable pageable) implements ProcessorRequest {
}
