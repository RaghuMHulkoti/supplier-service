package com.learning.supplier_service.gateway.mysql.supplier.repository;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierWarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SupplierWarehouseRepository extends JpaRepository<SupplierWarehouseEntity, UUID> {
    boolean existsBySupplier_IdAndCodeIgnoreCase(UUID supplierId, String code);

    List<SupplierWarehouseEntity> findAllBySupplier_IdOrderByCreatedAtAsc(UUID supplierId);
}
