package com.learning.supplier_service.core.service.warehouse;

import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.exception.DuplicateWarehouseCodeException;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierWarehouseRepository;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierWarehouseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class WarehouseService implements Service<WarehouseServiceRequest, WarehouseServiceResponse> {
    private final SupplierWarehouseRepository warehouseRepository;

    public WarehouseService(SupplierWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseServiceResponse execute(WarehouseServiceRequest request) {
        return null;
    }

    public SupplierWarehouseEntity create(
            SupplierEntity supplier,
            String name,
            String code,
            String addressLine1,
            String addressLine2,
            String city,
            String state,
            String postalCode,
            String country,
            BigDecimal geoLat,
            BigDecimal geoLng
    ) {
        if (warehouseRepository.existsBySupplier_IdAndCodeIgnoreCase(supplier.getId(), code)) {
            throw new DuplicateWarehouseCodeException(code);
        }

        return warehouseRepository.save(new SupplierWarehouseEntity(
                UUID.randomUUID(),
                supplier,
                name,
                code.toUpperCase(Locale.ROOT),
                addressLine1,
                addressLine2,
                city,
                state,
                postalCode,
                country.toUpperCase(Locale.ROOT),
                geoLat,
                geoLng
        ));
    }

    public List<SupplierWarehouseEntity> list(UUID supplierId) {
        return warehouseRepository.findAllBySupplier_IdOrderByCreatedAtAsc(supplierId);
    }
}
