package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record GetOfferUsecaseRequest(UUID supplierId, UUID offerId) implements UsecaseRequest {

    /** Fetch by offerId only — no supplier scope (used by internal APIs) */
    public static GetOfferUsecaseRequest byId(UUID offerId) {
        return new GetOfferUsecaseRequest(null, offerId);
    }

    /** Fetch scoped to a specific supplier (used by supplier-facing APIs) */
    public static GetOfferUsecaseRequest bySupplierAndId(UUID supplierId, UUID offerId) {
        return new GetOfferUsecaseRequest(supplierId, offerId);
    }
}
