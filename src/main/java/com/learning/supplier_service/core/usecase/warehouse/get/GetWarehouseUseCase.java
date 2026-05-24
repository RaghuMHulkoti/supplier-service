package com.learning.supplier_service.core.usecase.warehouse.get;

import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.exception.WarehouseNotFoundException;
import com.learning.supplier_service.gateway.WarehouseGateway;
import org.springframework.stereotype.Component;

@Component
public class GetWarehouseUseCase implements Usecase<GetWarehouseUsecaseRequest, GetWarehouseUsecaseResponse> {

    private final WarehouseGateway warehouseGateway;

    public GetWarehouseUseCase(WarehouseGateway warehouseGateway) {
        this.warehouseGateway = warehouseGateway;
    }

    @Override
    public GetWarehouseUsecaseResponse execute(GetWarehouseUsecaseRequest request) {
        var warehouse = warehouseGateway.findByIdAndSupplierId(request.warehouseId(), request.supplierId())
                .orElseThrow(() -> new WarehouseNotFoundException(request.warehouseId()));
        return new GetWarehouseUsecaseResponse(warehouse);
    }
}
