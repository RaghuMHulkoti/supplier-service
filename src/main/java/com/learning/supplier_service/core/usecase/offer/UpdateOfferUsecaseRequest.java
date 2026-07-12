package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateOfferUsecaseRequest(
        UUID supplierId,
        UUID offerId,
        BigDecimal price,
        Short handlingDays,
        Boolean active
) implements UsecaseRequest {
}
