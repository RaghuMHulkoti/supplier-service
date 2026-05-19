package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record ListActiveOffersByProductUsecaseRequest(UUID productId) implements UsecaseRequest {
}

