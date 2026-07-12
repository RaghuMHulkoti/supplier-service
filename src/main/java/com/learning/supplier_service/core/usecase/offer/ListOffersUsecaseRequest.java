package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public record ListOffersUsecaseRequest(UUID supplierId, Pageable pageable) implements UsecaseRequest {
}

