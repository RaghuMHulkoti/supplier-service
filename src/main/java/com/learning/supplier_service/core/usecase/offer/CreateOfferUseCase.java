//package com.learning.supplier_service.core.usecase.offer;
//
//import com.learning.supplier_service.core.service.offer.OfferService;
//import com.learning.supplier_service.core.service.outbox.OutboxService;
//import com.learning.supplier_service.core.service.supplier.SupplierService;
//import com.learning.supplier_service.core.usecase.Usecase;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class CreateOfferUseCase implements Usecase<CreateOfferUsecaseRequest, CreateOfferUsecaseResponse> {
//    private final SupplierService supplierService;
//    private final OfferService offerService;
//    private final OutboxService outboxService;
//
//    public CreateOfferUseCase(
//            SupplierService supplierService,
//            OfferService offerService,
//            OutboxService outboxService
//    ) {
//        this.supplierService = supplierService;
//        this.offerService = offerService;
//        this.outboxService = outboxService;
//    }
//
//    @Override
//    @Transactional
//    public CreateOfferUsecaseResponse execute(CreateOfferUsecaseRequest request) {
//        var supplier = supplierService.getRequired(request.supplierId());
//        var offer = offerService.create(
//                supplier,
//                request.productId(),
//                request.supplierSku(),
//                request.price(),
//                request.currency(),
//                request.condition(),
//                request.handlingDays()
//        );
//
//        outboxService.append(offer.getId(), "OFFER_CREATED", new OfferCreatedEvent(
//                offer.getId(),
//                supplier.getId(),
//                offer.getProductId(),
//                offer.getPrice(),
//                offer.getCurrency(),
//                offer.isActive()
//        ));
//
//        return new CreateOfferUsecaseResponse(
//                offer.getId(),
//                offer.isActive(),
//                offer.getListedAt()
//        );
//    }
//
//    private record OfferCreatedEvent(
//            java.util.UUID offerId,
//            java.util.UUID supplierId,
//            java.util.UUID productId,
//            java.math.BigDecimal price,
//            String currency,
//            Boolean active
//    ) {
//    }
//}
