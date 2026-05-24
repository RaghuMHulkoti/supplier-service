package com.learning.supplier_service.core.service.warehouse.create;

import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.exception.SupplierNotFoundException;
import com.learning.supplier_service.gateway.SupplierGateway;
import com.learning.supplier_service.gateway.WarehouseGateway;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class CreateWarehouseService
        implements Service<CreateWarehouseServiceRequest, CreateWarehouseServiceResponse> {

    private final SupplierGateway supplierGateway;
    private final WarehouseGateway warehouseGateway;

    public CreateWarehouseService(
            SupplierGateway supplierGateway,
            WarehouseGateway warehouseGateway
    ) {
        this.supplierGateway = supplierGateway;
        this.warehouseGateway = warehouseGateway;
    }

    @Override
    public CreateWarehouseServiceResponse execute(CreateWarehouseServiceRequest request) {

        SupplierEntity supplier = getSupplier(request.getSupplierId());

        SupplierWarehouseEntity warehouseToCreate = buildWarehouseEntity(
                supplier,
                request
        );

        SupplierWarehouseEntity savedWarehouse =
                warehouseGateway.saveSupplierWarehouse(warehouseToCreate);

        return mapResponse(savedWarehouse);
    }

    private SupplierEntity getSupplier(UUID supplierId) {
        return supplierGateway.getSupplierById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
    }

    private SupplierWarehouseEntity buildWarehouseEntity(
            SupplierEntity supplier,
            CreateWarehouseServiceRequest request
    ) {

        return SupplierWarehouseEntity.builder()
                .id(UUID.randomUUID())
                .supplier(supplier)
                .name(request.getName())
                .code(toUpperCase(request.getCode()))
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .postalCode(request.getPostalCode())
                .country(toUpperCase(request.getCountry()))
                .geoLat(request.getGeoLat())
                .geoLng(request.getGeoLng())
                .build();
    }

    private CreateWarehouseServiceResponse mapResponse(
            SupplierWarehouseEntity warehouse
    ) {

        return CreateWarehouseServiceResponse.builder()
                .warehouseId(warehouse.getId())
                .supplierId(warehouse.getSupplier().getId())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .active(warehouse.isActive())
                .createdAt(warehouse.getCreatedAt())
                .build();
    }

    private String toUpperCase(String value) {
        return value == null
                ? null
                : value.toUpperCase(Locale.ROOT);
    }
}