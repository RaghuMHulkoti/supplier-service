package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record GetOfferUsecaseRequest(UUID supplierId, UUID offerId) implements UsecaseRequest {
}

