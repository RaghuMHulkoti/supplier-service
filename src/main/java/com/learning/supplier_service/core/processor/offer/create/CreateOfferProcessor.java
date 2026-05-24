package com.learning.supplier_service.core.processor.offer.create;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.offer.CreateOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.CreateOfferUsecaseRequest;
import com.learning.supplier_service.core.usecase.offer.GetOfferUseCase;
import com.learning.supplier_service.core.usecase.offer.GetOfferUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateOfferProcessor implements Processor<CreateOfferProcessorRequest, CreateOfferProcessorResponse> {

    private final CreateOfferUseCase createOfferUseCase;
    private final GetOfferUseCase getOfferUseCase;

    public CreateOfferProcessor(CreateOfferUseCase createOfferUseCase, GetOfferUseCase getOfferUseCase) {
        this.createOfferUseCase = createOfferUseCase;
        this.getOfferUseCase = getOfferUseCase;
    }

    @Override
    public CreateOfferProcessorResponse process(CreateOfferProcessorRequest request) {
        var created = createOfferUseCase.execute(new CreateOfferUsecaseRequest(
                request.getSupplierId(), request.getProductId(), request.getSupplierSku(),
                request.getPrice(), request.getCurrency(), request.getCondition(), request.getHandlingDays()
        ));
        var offer = getOfferUseCase.execute(GetOfferUsecaseRequest.byId(created.offerId())).offer();
        return new CreateOfferProcessorResponse(offer);
    }
}
