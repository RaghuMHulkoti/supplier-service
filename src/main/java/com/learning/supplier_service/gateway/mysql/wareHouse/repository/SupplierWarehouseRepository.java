package com.learning.supplier_service.gateway.mysql.wareHouse.repository;

import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierWarehouseRepository extends JpaRepository<SupplierWarehouseEntity, UUID> {
    boolean existsBySupplier_IdAndCodeIgnoreCase(UUID supplierId, String code);

    Optional<SupplierWarehouseEntity> findByIdAndSupplier_Id(UUID id, UUID supplierId);

    List<SupplierWarehouseEntity> findAllBySupplier_IdOrderByCreatedAtAsc(UUID supplierId);
}
