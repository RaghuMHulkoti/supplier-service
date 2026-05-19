//package com.learning.supplier_service.core.processor.offer;
//
//import com.learning.supplier_service.core.usecase.offer.CreateOfferUseCase;
//import com.learning.supplier_service.core.usecase.offer.CreateOfferUsecaseRequest;
//import com.learning.supplier_service.core.usecase.offer.CreateOfferUsecaseResponse;
//import com.learning.supplier_service.core.usecase.offer.GetOfferByIdUseCase;
//import com.learning.supplier_service.core.usecase.offer.GetOfferByIdUsecaseRequest;
//import com.learning.supplier_service.core.usecase.offer.GetOfferUseCase;
//import com.learning.supplier_service.core.usecase.offer.GetOfferUsecaseRequest;
//import com.learning.supplier_service.core.usecase.offer.ListActiveOffersByProductUseCase;
//import com.learning.supplier_service.core.usecase.offer.ListActiveOffersByProductUsecaseRequest;
//import com.learning.supplier_service.core.usecase.offer.ListOffersUseCase;
//import com.learning.supplier_service.core.usecase.offer.ListOffersUsecaseRequest;
//import com.learning.supplier_service.core.usecase.offer.UpdateOfferUseCase;
//import com.learning.supplier_service.core.usecase.offer.UpdateOfferUsecaseRequest;
//import com.learning.supplier_service.core.usecase.offer.UpdateOfferUsecaseResponse;
//import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierProductEntity;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.UUID;
//
//@Component
//public class OfferProcessor {
//    private final CreateOfferUseCase createOfferUseCase;
//    private final UpdateOfferUseCase updateOfferUseCase;
//    private final GetOfferUseCase getOfferUseCase;
//    private final GetOfferByIdUseCase getOfferByIdUseCase;
//    private final ListOffersUseCase listOffersUseCase;
//    private final ListActiveOffersByProductUseCase listActiveOffersByProductUseCase;
//
//    public OfferProcessor(
//            CreateOfferUseCase createOfferUseCase,
//            UpdateOfferUseCase updateOfferUseCase,
//            GetOfferUseCase getOfferUseCase,
//            GetOfferByIdUseCase getOfferByIdUseCase,
//            ListOffersUseCase listOffersUseCase,
//            ListActiveOffersByProductUseCase listActiveOffersByProductUseCase
//    ) {
//        this.createOfferUseCase = createOfferUseCase;
//        this.updateOfferUseCase = updateOfferUseCase;
//        this.getOfferUseCase = getOfferUseCase;
//        this.getOfferByIdUseCase = getOfferByIdUseCase;
//        this.listOffersUseCase = listOffersUseCase;
//        this.listActiveOffersByProductUseCase = listActiveOffersByProductUseCase;
//    }
//
//    public CreateOfferUsecaseResponse create(CreateOfferUsecaseRequest request) {
//        return createOfferUseCase.execute(request);
//    }
//
//    public UpdateOfferUsecaseResponse update(UpdateOfferUsecaseRequest request) {
//        return updateOfferUseCase.execute(request);
//    }
//
//    public SupplierProductEntity get(UUID supplierId, UUID offerId) {
//        return getOfferUseCase.execute(new GetOfferUsecaseRequest(supplierId, offerId)).offer();
//    }
//
//    public SupplierProductEntity get(UUID offerId) {
//        return getOfferByIdUseCase.execute(new GetOfferByIdUsecaseRequest(offerId)).offer();
//    }
//
//    public Page<SupplierProductEntity> list(UUID supplierId, org.springframework.data.domain.Pageable pageable) {
//        return listOffersUseCase.execute(new ListOffersUsecaseRequest(supplierId, pageable)).offers();
//    }
//
//    public List<SupplierProductEntity> listActiveByProduct(UUID productId) {
//        return listActiveOffersByProductUseCase.execute(new ListActiveOffersByProductUsecaseRequest(productId)).offers();
//    }
//}
//
//
//
