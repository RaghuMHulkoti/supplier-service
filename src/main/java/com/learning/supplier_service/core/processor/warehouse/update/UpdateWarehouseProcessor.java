package com.learning.supplier_service.core.processor.warehouse.update;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.warehouse.update.UpdateWarehouseUseCase;
import com.learning.supplier_service.core.usecase.warehouse.update.UpdateWarehouseUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateWarehouseProcessor implements Processor<UpdateWarehouseProcessorRequest, UpdateWarehouseProcessorResponse> {

    private final UpdateWarehouseUseCase updateWarehouseUseCase;

    public UpdateWarehouseProcessor(UpdateWarehouseUseCase updateWarehouseUseCase) {
        this.updateWarehouseUseCase = updateWarehouseUseCase;
    }

    @Override
    public UpdateWarehouseProcessorResponse process(UpdateWarehouseProcessorRequest request) {
        var response = updateWarehouseUseCase.execute(new UpdateWarehouseUsecaseRequest(
                request.getSupplierId(), request.getWarehouseId(),
                request.getName(), request.getAddressLine1(), request.getAddressLine2(),
                request.getCity(), request.getState(), request.getPostalCode(),
                request.getCountry(), request.getGeoLat(), request.getGeoLng()
        ));
        return new UpdateWarehouseProcessorResponse(response.warehouse());
    }
}
