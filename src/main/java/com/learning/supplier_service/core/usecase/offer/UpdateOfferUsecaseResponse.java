package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateOfferUsecaseResponse(
        UUID offerId,
        UUID productId,
        BigDecimal price,
        String currency,
        OfferCondition condition,
        Short handlingDays,
        Boolean active
) implements UsecaseResponse {
}
