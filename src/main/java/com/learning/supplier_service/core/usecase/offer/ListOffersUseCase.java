package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.create.CreateOfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class ListOffersUseCase implements Usecase<ListOffersUsecaseRequest, ListOffersUsecaseResponse> {
    private final CreateOfferService createOfferService;

    public ListOffersUseCase(CreateOfferService createOfferService) {
        this.createOfferService = createOfferService;
    }

    @Override
    public ListOffersUsecaseResponse execute(ListOffersUsecaseRequest request) {
        return new ListOffersUsecaseResponse(createOfferService.list(request.supplierId(), request.pageable()));
    }
}

