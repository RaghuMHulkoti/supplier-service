package com.learning.supplier_service.core.service.offer.create;

import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.exception.DuplicateOfferException;
import com.learning.supplier_service.gateway.SupplierProductGateway;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;

import java.util.Locale;
import java.util.UUID;

@org.springframework.stereotype.Component
public class CreateOfferService implements Service<CreateOfferServiceRequest, CreateOfferServiceResponse> {

    private final SupplierProductGateway supplierProductGateway;

    public CreateOfferService(SupplierProductGateway supplierProductGateway) {
        this.supplierProductGateway = supplierProductGateway;
    }

    @Override
    public CreateOfferServiceResponse execute(CreateOfferServiceRequest request) {
        if (supplierProductGateway.existsBySupplierIdAndProductId(
                request.getSupplier().getId(), request.getProductId())) {
            throw new DuplicateOfferException("Offer already exists for supplier and product");
        }
        if (supplierProductGateway.existsBySupplierIdAndSupplierSkuIgnoreCase(
                request.getSupplier().getId(), request.getSupplierSku())) {
            throw new DuplicateOfferException("Supplier SKU already exists: " + request.getSupplierSku());
        }
        var saved = supplierProductGateway.save(new SupplierProductEntity(
                UUID.randomUUID(),
                request.getSupplier(),
                request.getProductId(),
                request.getSupplierSku(),
                request.getPrice(),
                request.getCurrency().toUpperCase(Locale.ROOT),
                request.getCondition(),
                request.getHandlingDays()
        ));
        return CreateOfferServiceResponse.builder().offer(saved).build();
    }

//    public SupplierProductEntity getRequired(UUID offerId) {
//        return supplierProductGateway.findById(offerId)
//                .orElseThrow(() -> new OfferNotFoundException(offerId));
//    }
//
//    public SupplierProductEntity getRequired(UUID supplierId, UUID offerId) {
//        return supplierProductGateway.findByIdAndSupplierId(offerId, supplierId)
//                .orElseThrow(() -> new OfferNotFoundException(offerId));
//    }
//
//    public SupplierProductEntity save(SupplierProductEntity offer) {
//        return supplierProductGateway.save(offer);
//    }
//
//    public Page<SupplierProductEntity> list(UUID supplierId, Pageable pageable) {
//        return supplierProductGateway.findAllBySupplierId(supplierId, pageable);
//    }
//
//    public List<SupplierProductEntity> listActiveByProduct(UUID productId) {
//        return supplierProductGateway.findAllByProductIdAndActiveTrueOrderByPriceAsc(productId);
//    }
}
