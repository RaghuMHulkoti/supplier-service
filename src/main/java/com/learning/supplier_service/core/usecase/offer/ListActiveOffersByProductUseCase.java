package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class ListActiveOffersByProductUseCase implements Usecase<ListActiveOffersByProductUsecaseRequest, ListActiveOffersByProductUsecaseResponse> {
    private final OfferService offerService;

    public ListActiveOffersByProductUseCase(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public ListActiveOffersByProductUsecaseResponse execute(ListActiveOffersByProductUsecaseRequest request) {
        return new ListActiveOffersByProductUsecaseResponse(offerService.listActiveByProduct(request.productId()));
    }
}

