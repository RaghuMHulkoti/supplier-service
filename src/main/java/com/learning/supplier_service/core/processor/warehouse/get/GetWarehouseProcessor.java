package com.learning.supplier_service.core.processor.warehouse.get;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.warehouse.get.GetWarehouseUseCase;
import com.learning.supplier_service.core.usecase.warehouse.get.GetWarehouseUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class GetWarehouseProcessor implements Processor<GetWarehouseProcessorRequest, GetWarehouseProcessorResponse> {

    private final GetWarehouseUseCase getWarehouseUseCase;

    public GetWarehouseProcessor(GetWarehouseUseCase getWarehouseUseCase) {
        this.getWarehouseUseCase = getWarehouseUseCase;
    }

    @Override
    public GetWarehouseProcessorResponse process(GetWarehouseProcessorRequest request) {
        var response = getWarehouseUseCase.execute(
                new GetWarehouseUsecaseRequest(request.supplierId(), request.warehouseId())
        );
        return new GetWarehouseProcessorResponse(response.warehouse());
    }
}
