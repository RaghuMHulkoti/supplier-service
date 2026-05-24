package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.create.CreateOfferServiceRequest;
import com.learning.supplier_service.core.service.offer.create.CreateOfferService;
import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.core.service.supplier.create.CreateSupplierService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateOfferUseCase implements Usecase<CreateOfferUsecaseRequest, CreateOfferUsecaseResponse> {

    private final CreateSupplierService createSupplierService;
    private final CreateOfferService createOfferService;
    private final OutboxService outboxService;

    public CreateOfferUseCase(
            CreateSupplierService createSupplierService,
            CreateOfferService createOfferService,
            OutboxService outboxService
    ) {
        this.createSupplierService = createSupplierService;
        this.createOfferService = createOfferService;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public CreateOfferUsecaseResponse execute(CreateOfferUsecaseRequest request) {
        var supplier = createSupplierService.getRequired(request.supplierId());

        var serviceRequest = CreateOfferServiceRequest.builder()
                .supplier(supplier)
                .productId(request.productId())
                .supplierSku(request.supplierSku())
                .price(request.price())
                .currency(request.currency())
                .condition(request.condition())
                .handlingDays(request.handlingDays())
                .build();

        var offer = createOfferService.execute(serviceRequest).getOffer();

        outboxService.append(offer.getId(), "OFFER_CREATED", new OfferCreatedEvent(
                offer.getId(), supplier.getId(), offer.getProductId(),
                offer.getPrice(), offer.getCurrency(), offer.isActive()
        ));

        return new CreateOfferUsecaseResponse(offer.getId(), offer.isActive(), offer.getListedAt());
    }

    private record OfferCreatedEvent(
            java.util.UUID offerId,
            java.util.UUID supplierId,
            java.util.UUID productId,
            java.math.BigDecimal price,
            String currency,
            Boolean active
    ) {}
}
