package com.learning.supplier_service.core.usecase.warehouse.status;

import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.exception.WarehouseNotFoundException;
import com.learning.supplier_service.gateway.WarehouseGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ToggleWarehouseStatusUseCase implements Usecase<ToggleWarehouseStatusUsecaseRequest, ToggleWarehouseStatusUsecaseResponse> {

    private final WarehouseGateway warehouseGateway;

    public ToggleWarehouseStatusUseCase(WarehouseGateway warehouseGateway) {
        this.warehouseGateway = warehouseGateway;
    }

    @Override
    @Transactional
    public ToggleWarehouseStatusUsecaseResponse execute(ToggleWarehouseStatusUsecaseRequest request) {
        var warehouse = warehouseGateway.findByIdAndSupplierId(request.warehouseId(), request.supplierId())
                .orElseThrow(() -> new WarehouseNotFoundException(request.warehouseId()));
        warehouse.setActive(request.active());
        var saved = warehouseGateway.saveSupplierWarehouse(warehouse);
        return new ToggleWarehouseStatusUsecaseResponse(saved.getId(), saved.isActive());
    }
}
