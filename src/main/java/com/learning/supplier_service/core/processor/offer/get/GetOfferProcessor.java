package com.learning.supplier_service.core.processor.offer.get;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.offer.GetOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.GetOfferUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class GetOfferProcessor implements Processor<GetOfferProcessorRequest, GetOfferProcessorResponse> {

    private final GetOfferUseCase getOfferUseCase;

    public GetOfferProcessor(GetOfferUseCase getOfferUseCase) {
        this.getOfferUseCase = getOfferUseCase;
    }

    @Override
    public GetOfferProcessorResponse process(GetOfferProcessorRequest request) {
        var usecaseRequest = request.supplierId() != null
                ? GetOfferUsecaseRequest.bySupplierAndId(request.supplierId(), request.offerId())
                : GetOfferUsecaseRequest.byId(request.offerId());
        return new GetOfferProcessorResponse(getOfferUseCase.execute(usecaseRequest).offer());
    }
}
