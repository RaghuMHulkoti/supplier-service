package com.learning.supplier_service.exception;

import java.util.UUID;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(UUID offerId) {
        super("Offer not found: " + offerId);
    }
}
