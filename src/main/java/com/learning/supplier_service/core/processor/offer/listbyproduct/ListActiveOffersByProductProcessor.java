package com.learning.supplier_service.core.processor.offer.listbyproduct;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.offer.ListActiveOffersByProductUseCase;
import com.learning.supplier_service.core.usecase.offer.ListActiveOffersByProductUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class ListActiveOffersByProductProcessor implements Processor<ListActiveOffersByProductProcessorRequest, ListActiveOffersByProductProcessorResponse> {

    private final ListActiveOffersByProductUseCase listActiveOffersByProductUseCase;

    public ListActiveOffersByProductProcessor(ListActiveOffersByProductUseCase listActiveOffersByProductUseCase) {
        this.listActiveOffersByProductUseCase = listActiveOffersByProductUseCase;
    }

    @Override
    public ListActiveOffersByProductProcessorResponse process(ListActiveOffersByProductProcessorRequest request) {
        var response = listActiveOffersByProductUseCase.execute(
                new ListActiveOffersByProductUsecaseRequest(request.productId())
        );
        return new ListActiveOffersByProductProcessorResponse(response.offers());
    }
}
