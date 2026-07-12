package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.create.CreateOfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class GetOfferUseCase implements Usecase<GetOfferUsecaseRequest, GetOfferUsecaseResponse> {

    private final CreateOfferService createOfferService;

    public GetOfferUseCase(CreateOfferService createOfferService) {
        this.createOfferService = createOfferService;
    }

    @Override
    public GetOfferUsecaseResponse execute(GetOfferUsecaseRequest request) {
        var offer = request.supplierId() != null
                ? createOfferService.getRequired(request.supplierId(), request.offerId())
                : createOfferService.getRequired(request.offerId());
        return new GetOfferUsecaseResponse(offer);
    }
}
