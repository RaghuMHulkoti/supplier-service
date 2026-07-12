package com.learning.supplier_service.core.processor.warehouse.status;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.warehouse.status.ToggleWarehouseStatusUseCase;
import com.learning.supplier_service.core.usecase.warehouse.status.ToggleWarehouseStatusUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class ToggleWarehouseStatusProcessor implements Processor<ToggleWarehouseStatusProcessorRequest, ToggleWarehouseStatusProcessorResponse> {

    private final ToggleWarehouseStatusUseCase toggleWarehouseStatusUseCase;

    public ToggleWarehouseStatusProcessor(ToggleWarehouseStatusUseCase toggleWarehouseStatusUseCase) {
        this.toggleWarehouseStatusUseCase = toggleWarehouseStatusUseCase;
    }

    @Override
    public ToggleWarehouseStatusProcessorResponse process(ToggleWarehouseStatusProcessorRequest request) {
        var response = toggleWarehouseStatusUseCase.execute(new ToggleWarehouseStatusUsecaseRequest(
                request.getSupplierId(), request.getWarehouseId(), request.isActive()
        ));
        return new ToggleWarehouseStatusProcessorResponse(response.warehouseId(), response.active());
    }
}
