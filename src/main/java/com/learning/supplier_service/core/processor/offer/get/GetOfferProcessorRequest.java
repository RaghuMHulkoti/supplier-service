package com.learning.supplier_service.core.processor.offer.get;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public record GetOfferProcessorRequest(UUID supplierId, UUID offerId) implements ProcessorRequest {

    public static GetOfferProcessorRequest byId(UUID offerId) {
        return new GetOfferProcessorRequest(null, offerId);
    }

    public static GetOfferProcessorRequest bySupplierAndId(UUID supplierId, UUID offerId) {
        return new GetOfferProcessorRequest(supplierId, offerId);
    }
}
