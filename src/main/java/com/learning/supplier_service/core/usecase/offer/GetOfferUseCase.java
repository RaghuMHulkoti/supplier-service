package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class GetOfferUseCase implements Usecase<GetOfferUsecaseRequest, GetOfferUsecaseResponse> {
    private final OfferService offerService;

    public GetOfferUseCase(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public GetOfferUsecaseResponse execute(GetOfferUsecaseRequest request) {
        return new GetOfferUsecaseResponse(offerService.getRequired(request.supplierId(), request.offerId()));
    }
}

