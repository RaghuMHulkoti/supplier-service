package com.learning.supplier_service.core.usecase.offer;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateOfferUseCase implements Usecase<UpdateOfferUsecaseRequest, UpdateOfferUsecaseResponse> {
    private final OfferService offerService;
    private final OutboxService outboxService;

    public UpdateOfferUseCase(OfferService offerService, OutboxService outboxService) {
        this.offerService = offerService;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public UpdateOfferUsecaseResponse execute(UpdateOfferUsecaseRequest request) {
        var offer = offerService.getRequired(request.supplierId(), request.offerId());

        if (request.price() != null && offer.updatePrice(request.price())) {
            outboxService.append(offer.getId(), "OFFER_PRICE_UPDATED", new OfferPriceUpdatedEvent(
                    offer.getId(),
                    offer.getProductId(),
                    offer.getPrice(),
                    offer.getCurrency()
            ));
        }
        if (request.handlingDays() != null) {
            offer.updateHandlingDays(request.handlingDays());
        }
        if (request.active() != null && offer.updateActive(request.active())) {
            if (request.active()) {
                outboxService.append(offer.getId(), "OFFER_ACTIVATED", new OfferActivatedEvent(
                        offer.getId(),
                        offer.getProductId()
                ));
            } else {
                outboxService.append(offer.getId(), "OFFER_DEACTIVATED", new OfferDeactivatedEvent(
                        offer.getId(),
                        offer.getProductId()
                ));
            }
        }

        var updated = offerService.save(offer);
        return new UpdateOfferUsecaseResponse(
                updated.getId(),
                updated.getProductId(),
                updated.getPrice(),
                updated.getCurrency(),
                updated.getCondition(),
                updated.getHandlingDays(),
                updated.isActive()
        );
    }

    private record OfferPriceUpdatedEvent(
            java.util.UUID offerId,
            java.util.UUID productId,
            java.math.BigDecimal price,
            String currency
    ) {
    }

    private record OfferDeactivatedEvent(
            java.util.UUID offerId,
            java.util.UUID productId
    ) {
    }

    private record OfferActivatedEvent(
            java.util.UUID offerId,
            java.util.UUID productId
    ) {
    }
}
