package com.learning.supplier_service.core.processor.offer.update;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.offer.GetOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.GetOfferUsecaseRequest;
import com.learning.supplier_service.core.usecase.offer.UpdateOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.UpdateOfferUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateOfferProcessor implements Processor<UpdateOfferProcessorRequest, UpdateOfferProcessorResponse> {

    private final UpdateOfferUseCase updateOfferUseCase;
    private final GetOfferUseCase getOfferUseCase;

    public UpdateOfferProcessor(UpdateOfferUseCase updateOfferUseCase, GetOfferUseCase getOfferUseCase) {
        this.updateOfferUseCase = updateOfferUseCase;
        this.getOfferUseCase = getOfferUseCase;
    }

    @Override
    public UpdateOfferProcessorResponse process(UpdateOfferProcessorRequest request) {
        updateOfferUseCase.execute(new UpdateOfferUsecaseRequest(
                request.getSupplierId(), request.getOfferId(),
                request.getPrice(), request.getHandlingDays(), request.getActive()
        ));
        var updated = getOfferUseCase.execute(
                GetOfferUsecaseRequest.bySupplierAndId(request.getSupplierId(), request.getOfferId())
        ).offer();
        return new UpdateOfferProcessorResponse(updated);
    }
}
