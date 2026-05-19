package com.learning.supplier_service.gateway.mysql.supplier.repository;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierProductRepository extends JpaRepository<SupplierProductEntity, UUID> {
    boolean existsBySupplier_IdAndProductId(UUID supplierId, UUID productId);

    boolean existsBySupplier_IdAndSupplierSkuIgnoreCase(UUID supplierId, String supplierSku);

    Optional<SupplierProductEntity> findByIdAndSupplier_Id(UUID id, UUID supplierId);

    Page<SupplierProductEntity> findAllBySupplier_Id(UUID supplierId, Pageable pageable);

    List<SupplierProductEntity> findAllByProductIdAndActiveTrueOrderByPriceAsc(UUID productId);
}
