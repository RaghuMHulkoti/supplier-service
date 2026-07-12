package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateOfferUsecaseResponse(
        UUID offerId,
        Boolean active,
        OffsetDateTime listedAt
) implements UsecaseResponse {
}
