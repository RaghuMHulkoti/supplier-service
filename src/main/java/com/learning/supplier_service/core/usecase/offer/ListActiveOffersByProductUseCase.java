package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.create.CreateOfferService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class ListActiveOffersByProductUseCase implements Usecase<ListActiveOffersByProductUsecaseRequest, ListActiveOffersByProductUsecaseResponse> {
    private final CreateOfferService createOfferService;

    public ListActiveOffersByProductUseCase(CreateOfferService createOfferService) {
        this.createOfferService = createOfferService;
    }

    @Override
    public ListActiveOffersByProductUsecaseResponse execute(ListActiveOffersByProductUsecaseRequest request) {
        return new ListActiveOffersByProductUsecaseResponse(createOfferService.listActiveByProduct(request.productId()));
    }
}

