package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class GetOfferByIdUseCase implements Usecase<GetOfferByIdUsecaseRequest, GetOfferByIdUsecaseResponse> {
    private final OfferService offerService;

    public GetOfferByIdUseCase(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public GetOfferByIdUsecaseResponse execute(GetOfferByIdUsecaseRequest request) {
        return new GetOfferByIdUsecaseResponse(offerService.getRequired(request.offerId()));
    }
}

