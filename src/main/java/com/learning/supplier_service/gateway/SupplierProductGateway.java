package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierProductGateway {

    boolean existsBySupplierIdAndProductId(UUID supplierId, UUID productId);

    boolean existsBySupplierIdAndSupplierSkuIgnoreCase(UUID supplierId, String supplierSku);

    Optional<SupplierProductEntity> findById(UUID offerId);

    Optional<SupplierProductEntity> findByIdAndSupplierId(UUID offerId, UUID supplierId);

    Page<SupplierProductEntity> findAllBySupplierId(UUID supplierId, Pageable pageable);

    List<SupplierProductEntity> findAllByProductIdAndActiveTrueOrderByPriceAsc(UUID productId);

    SupplierProductEntity save(SupplierProductEntity supplierProductEntity);
}

