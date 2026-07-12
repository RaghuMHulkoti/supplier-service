package com.learning.supplier_service.core.processor.offer.list;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public record ListOffersProcessorRequest(UUID supplierId, Pageable pageable) implements ProcessorRequest {
}
