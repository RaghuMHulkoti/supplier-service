package com.learning.supplier_service.core.processor.warehouse.list;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.warehouse.ListWarehouseUseCase;
import com.learning.supplier_service.core.usecase.warehouse.ListWarehouseUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class ListWarehousesProcessor implements Processor<ListWarehousesProcessorRequest, ListWarehousesProcessorResponse> {

    private final ListWarehouseUseCase listWarehouseUseCase;

    public ListWarehousesProcessor(ListWarehouseUseCase listWarehouseUseCase) {
        this.listWarehouseUseCase = listWarehouseUseCase;
    }

    @Override
    public ListWarehousesProcessorResponse process(ListWarehousesProcessorRequest request) {
        var response = listWarehouseUseCase.execute(new ListWarehouseUsecaseRequest(request.supplierId()));
        return new ListWarehousesProcessorResponse(response.warehouses());
    }
}
