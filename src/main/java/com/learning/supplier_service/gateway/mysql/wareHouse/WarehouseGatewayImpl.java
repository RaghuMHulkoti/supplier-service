package com.learning.supplier_service.gateway.mysql.wareHouse;

import com.learning.supplier_service.gateway.WarehouseGateway;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;
import com.learning.supplier_service.gateway.mysql.wareHouse.repository.SupplierWarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WarehouseGatewayImpl implements WarehouseGateway {

    private final SupplierWarehouseRepository supplierWarehouseRepository;

    @Override
    public boolean existsBySupplierIdAndCodeIgnoreCase(UUID supplierId, String code) {
        return supplierWarehouseRepository.existsBySupplier_IdAndCodeIgnoreCase(supplierId, code);
    }

    @Override
    public Optional<SupplierWarehouseEntity> findByIdAndSupplierId(UUID warehouseId, UUID supplierId) {
        return supplierWarehouseRepository.findByIdAndSupplier_Id(warehouseId, supplierId);
    }

    @Override
    public List<SupplierWarehouseEntity> findAllWarehousesBySupplierIdOrderByCreatedAtAsc(UUID supplierId) {
        return supplierWarehouseRepository.findAllBySupplier_IdOrderByCreatedAtAsc(supplierId);
    }

    @Override
    public SupplierWarehouseEntity saveSupplierWarehouse(SupplierWarehouseEntity supplierWarehouseEntity) {
        return supplierWarehouseRepository.save(supplierWarehouseEntity);
    }
}

