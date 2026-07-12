package com.learning.supplier_service.gateway.mysql.supplierproduct;

import com.learning.supplier_service.gateway.SupplierProductGateway;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;
import com.learning.supplier_service.gateway.mysql.supplierproduct.repository.SupplierProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupplierProductGatewayImpl implements SupplierProductGateway {

    private final SupplierProductRepository supplierProductRepository;

    @Override
    public boolean existsBySupplierIdAndProductId(UUID supplierId, UUID productId) {
        return supplierProductRepository.existsBySupplier_IdAndProductId(supplierId, productId);
    }

    @Override
    public boolean existsBySupplierIdAndSupplierSkuIgnoreCase(UUID supplierId, String supplierSku) {
        return supplierProductRepository.existsBySupplier_IdAndSupplierSkuIgnoreCase(supplierId, supplierSku);
    }

    @Override
    public Optional<SupplierProductEntity> findById(UUID offerId) {
        return supplierProductRepository.findById(offerId);
    }

    @Override
    public Optional<SupplierProductEntity> findByIdAndSupplierId(UUID offerId, UUID supplierId) {
        return supplierProductRepository.findByIdAndSupplier_Id(offerId, supplierId);
    }

    @Override
    public Page<SupplierProductEntity> findAllBySupplierId(UUID supplierId, Pageable pageable) {
        return supplierProductRepository.findAllBySupplier_Id(supplierId, pageable);
    }

    @Override
    public List<SupplierProductEntity> findAllByProductIdAndActiveTrueOrderByPriceAsc(UUID productId) {
        return supplierProductRepository.findAllByProductIdAndActiveTrueOrderByPriceAsc(productId);
    }

    @Override
    public SupplierProductEntity save(SupplierProductEntity supplierProductEntity) {
        return supplierProductRepository.save(supplierProductEntity);
    }
}

