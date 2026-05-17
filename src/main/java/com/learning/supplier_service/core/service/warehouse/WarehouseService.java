package com.learning.supplier_service.core.service.warehouse;

import com.learning.supplier_service.exception.DuplicateWarehouseCodeException;
import com.learning.supplier_service.gateway.SupplierWarehouseRepository;
import com.learning.supplier_service.gateway.entity.SupplierEntity;
import com.learning.supplier_service.gateway.entity.SupplierWarehouseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class WarehouseService {
    private final SupplierWarehouseRepository warehouseRepository;

    public WarehouseService(SupplierWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
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
