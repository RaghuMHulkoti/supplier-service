package com.learning.supplier_service.core.processor.offer.listbyproduct;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public record ListActiveOffersByProductProcessorRequest(UUID productId) implements ProcessorRequest {
}
