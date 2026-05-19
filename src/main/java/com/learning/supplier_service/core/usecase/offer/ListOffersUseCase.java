package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class ListOffersUseCase implements Usecase<ListOffersUsecaseRequest, ListOffersUsecaseResponse> {
    private final OfferService offerService;

    public ListOffersUseCase(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public ListOffersUsecaseResponse execute(ListOffersUsecaseRequest request) {
        return new ListOffersUsecaseResponse(offerService.list(request.supplierId(), request.pageable()));
    }
}

