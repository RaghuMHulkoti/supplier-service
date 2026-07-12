package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseGateway {

    boolean existsBySupplierIdAndCodeIgnoreCase(UUID supplierId, String code);

    Optional<SupplierWarehouseEntity> findByIdAndSupplierId(UUID warehouseId, UUID supplierId);

    List<SupplierWarehouseEntity> findAllWarehousesBySupplierIdOrderByCreatedAtAsc(UUID supplierId);

    SupplierWarehouseEntity saveSupplierWarehouse(SupplierWarehouseEntity supplierWarehouseEntity);
}

