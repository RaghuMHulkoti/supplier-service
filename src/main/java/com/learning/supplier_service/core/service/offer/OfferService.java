package com.learning.supplier_service.core.service.offer;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.exception.DuplicateOfferException;
import com.learning.supplier_service.exception.OfferNotFoundException;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierProductRepository;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class OfferService {
    private final SupplierProductRepository productRepository;

    public OfferService(SupplierProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public SupplierProductEntity create(
            SupplierEntity supplier,
            UUID productId,
            String supplierSku,
            BigDecimal price,
            String currency,
            OfferCondition condition,
            Short handlingDays
    ) {
        if (productRepository.existsBySupplier_IdAndProductId(supplier.getId(), productId)) {
            throw new DuplicateOfferException("Offer already exists for supplier and product");
        }
        if (productRepository.existsBySupplier_IdAndSupplierSkuIgnoreCase(supplier.getId(), supplierSku)) {
            throw new DuplicateOfferException("Supplier SKU already exists: " + supplierSku);
        }

        return productRepository.save(new SupplierProductEntity(
                UUID.randomUUID(),
                supplier,
                productId,
                supplierSku,
                price,
                currency.toUpperCase(Locale.ROOT),
                condition,
                handlingDays
        ));
    }

    public SupplierProductEntity getRequired(UUID offerId) {
        return productRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException(offerId));
    }

    public SupplierProductEntity getRequired(UUID supplierId, UUID offerId) {
        return productRepository.findByIdAndSupplier_Id(offerId, supplierId)
                .orElseThrow(() -> new OfferNotFoundException(offerId));
    }

    public SupplierProductEntity save(SupplierProductEntity offer) {
        return productRepository.save(offer);
    }

    public Page<SupplierProductEntity> list(UUID supplierId, Pageable pageable) {
        return productRepository.findAllBySupplier_Id(supplierId, pageable);
    }

    public List<SupplierProductEntity> listActiveByProduct(UUID productId) {
        return productRepository.findAllByProductIdAndActiveTrueOrderByPriceAsc(productId);
    }
}
