package com.learning.supplier_service.web.response;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.gateway.entity.SupplierProductEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record OfferResponse(
        UUID offerId,
        UUID supplierId,
        UUID productId,
        String supplierSku,
        BigDecimal price,
        String currency,
        OfferCondition condition,
        Short handlingDays,
        Boolean active,
        OffsetDateTime listedAt
) {
    public static OfferResponse from(SupplierProductEntity offer) {
        return new OfferResponse(
                offer.getId(),
                offer.getSupplierId(),
                offer.getProductId(),
                offer.getSupplierSku(),
                offer.getPrice(),
                offer.getCurrency(),
                offer.getCondition(),
                offer.getHandlingDays(),
                offer.isActive(),
                offer.getListedAt()
        );
    }
}
