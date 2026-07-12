package com.learning.supplier_service.core.usecase.warehouse.update;

import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.exception.WarehouseNotFoundException;
import com.learning.supplier_service.gateway.WarehouseGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateWarehouseUseCase implements Usecase<UpdateWarehouseUsecaseRequest, UpdateWarehouseUsecaseResponse> {

    private final WarehouseGateway warehouseGateway;

    public UpdateWarehouseUseCase(WarehouseGateway warehouseGateway) {
        this.warehouseGateway = warehouseGateway;
    }

    @Override
    @Transactional
    public UpdateWarehouseUsecaseResponse execute(UpdateWarehouseUsecaseRequest request) {
        var warehouse = warehouseGateway.findByIdAndSupplierId(request.warehouseId(), request.supplierId())
                .orElseThrow(() -> new WarehouseNotFoundException(request.warehouseId()));
        warehouse.update(
                request.name(), request.addressLine1(), request.addressLine2(),
                request.city(), request.state(), request.postalCode(),
                request.country(), request.geoLat(), request.geoLng()
        );
        return new UpdateWarehouseUsecaseResponse(warehouseGateway.saveSupplierWarehouse(warehouse));
    }
}
