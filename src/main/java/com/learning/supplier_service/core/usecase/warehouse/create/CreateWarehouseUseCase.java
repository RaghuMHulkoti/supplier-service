package com.learning.supplier_service.core.usecase.warehouse.create;

import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.core.service.warehouse.create.CreateWarehouseService;
import com.learning.supplier_service.core.service.warehouse.create.CreateWarehouseServiceRequest;
import com.learning.supplier_service.core.service.warehouse.create.CreateWarehouseServiceResponse;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateWarehouseUseCase implements Usecase<CreateWarehouseUsecaseRequest, CreateWarehouseUsecaseResponse> {
    private final CreateWarehouseService createWarehouseService;
    private final OutboxService outboxService;

    public CreateWarehouseUseCase(
            CreateWarehouseService createWarehouseService,
            OutboxService outboxService
    ) {
        this.createWarehouseService = createWarehouseService;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public CreateWarehouseUsecaseResponse execute(CreateWarehouseUsecaseRequest request) {
        var warehouse = createWarehouseService.execute(buildCreateWareHouseUsecaseRequest(request));

//        outboxService.append(warehouse.getId(), "SUPPLIER_WAREHOUSE_DECLARED", new SupplierWarehouseDeclaredEvent(
//                warehouse.getId(),
//                supplier.getId(),
//                warehouse.getCode(),
//                warehouse.getName()
//        ));
        return mapToResponse(warehouse, request.getSupplierId());

    }

    private static CreateWarehouseUsecaseResponse mapToResponse(CreateWarehouseServiceResponse serviceResponse, java.util.UUID supplierId) {
        return CreateWarehouseUsecaseResponse.builder()
                .warehouseId(serviceResponse.getWarehouseId())
                .supplierId(supplierId)
                .code(serviceResponse.getCode())
                .name(serviceResponse.getName())
                .active(serviceResponse.getActive())
                .createdAt(serviceResponse.getCreatedAt())
                .build();
    }

    private static CreateWarehouseServiceRequest buildCreateWareHouseUsecaseRequest(CreateWarehouseUsecaseRequest request) {
        return CreateWarehouseServiceRequest.builder()
                .supplierId(request.getSupplierId())
                .code(request.getCode())
                .name(request.getName())
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

    private record SupplierWarehouseDeclaredEvent(
            java.util.UUID warehouseId,
            java.util.UUID supplierId,
            String code,
            String name
    ) {
    }
}
