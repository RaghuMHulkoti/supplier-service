package com.learning.supplier_service.core.processor.offer.list;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.offer.ListOffersUseCase;
import com.learning.supplier_service.core.usecase.offer.ListOffersUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class ListOffersProcessor implements Processor<ListOffersProcessorRequest, ListOffersProcessorResponse> {

    private final ListOffersUseCase listOffersUseCase;

    public ListOffersProcessor(ListOffersUseCase listOffersUseCase) {
        this.listOffersUseCase = listOffersUseCase;
    }

    @Override
    public ListOffersProcessorResponse process(ListOffersProcessorRequest request) {
        var response = listOffersUseCase.execute(
                new ListOffersUsecaseRequest(request.supplierId(), request.pageable())
        );
        return new ListOffersProcessorResponse(response.offers());
    }
}
