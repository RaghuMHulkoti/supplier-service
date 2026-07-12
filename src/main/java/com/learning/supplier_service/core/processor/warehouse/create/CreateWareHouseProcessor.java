package com.learning.supplier_service.core.processor.warehouse.create;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.warehouse.create.CreateWarehouseUseCase;
import com.learning.supplier_service.core.usecase.warehouse.create.CreateWarehouseUsecaseRequest;
import com.learning.supplier_service.core.usecase.warehouse.create.CreateWarehouseUsecaseResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateWareHouseProcessor implements Processor<CreateWareHouseProcessorRequest, CreateWareHouseProcessorResponse> {
    private final CreateWarehouseUseCase createWarehouseUseCase;

    public CreateWareHouseProcessor(CreateWarehouseUseCase createWarehouseUseCase) {
        this.createWarehouseUseCase = createWarehouseUseCase;
    }

    @Override
    public CreateWareHouseProcessorResponse process(CreateWareHouseProcessorRequest request) {
        var usecaseResponse = createWarehouseUseCase.execute(buildCreateWarehouseUsecaseRequest(request));
        return mapToResponse(usecaseResponse);
    }

    private static CreateWareHouseProcessorResponse mapToResponse(CreateWarehouseUsecaseResponse usecaseResponse) {
        return CreateWareHouseProcessorResponse.builder()
                .warehouseId(usecaseResponse.getWarehouseId())
                .supplierId(usecaseResponse.getSupplierId())
                .code(usecaseResponse.getCode())
                .name(usecaseResponse.getName())
                .active(usecaseResponse.getActive())
                .createdAt(usecaseResponse.getCreatedAt())
                .build();
    }

    private static CreateWarehouseUsecaseRequest buildCreateWarehouseUsecaseRequest(CreateWareHouseProcessorRequest request) {
        return CreateWarehouseUsecaseRequest.builder()
                .supplierId(request.getSupplierId())
                .name(request.getName())
                .code(request.getCode())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .postalCode(request.getPostalCode())
                .country(request.getCountry())
                .geoLat(request.getGeoLat())
                .geoLng(request.getGeoLng())
                .build();
    }
}
