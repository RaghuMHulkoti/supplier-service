package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOfferUsecaseRequest(
        UUID supplierId,
        UUID productId,
        String supplierSku,
        BigDecimal price,
        String currency,
        OfferCondition condition,
        Short handlingDays
) implements UsecaseRequest {
}
