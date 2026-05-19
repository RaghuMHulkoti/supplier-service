//package com.learning.supplier_service.core.usecase.warehouse;
//
//import com.learning.supplier_service.core.service.outbox.OutboxService;
//import com.learning.supplier_service.core.service.supplier.SupplierService;
//import com.learning.supplier_service.core.service.warehouse.WarehouseService;
//import com.learning.supplier_service.core.usecase.Usecase;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class CreateWarehouseUseCase implements Usecase<CreateWarehouseUsecaseRequest, CreateWarehouseUsecaseResponse> {
//    private final SupplierService supplierService;
//    private final WarehouseService warehouseService;
//    private final OutboxService outboxService;
//
//    public CreateWarehouseUseCase(
//            SupplierService supplierService,
//            WarehouseService warehouseService,
//            OutboxService outboxService
//    ) {
//        this.supplierService = supplierService;
//        this.warehouseService = warehouseService;
//        this.outboxService = outboxService;
//    }
//
//    @Override
//    @Transactional
//    public CreateWarehouseUsecaseResponse execute(CreateWarehouseUsecaseRequest request) {
//        var supplier = supplierService.getRequired(request.supplierId());
//        var warehouse = warehouseService.create(
//                supplier,
//                request.name(),
//                request.code(),
//                request.addressLine1(),
//                request.addressLine2(),
//                request.city(),
//                request.state(),
//                request.postalCode(),
//                request.country(),
//                request.geoLat(),
//                request.geoLng()
//        );
//
//        outboxService.append(warehouse.getId(), "SUPPLIER_WAREHOUSE_DECLARED", new SupplierWarehouseDeclaredEvent(
//                warehouse.getId(),
//                supplier.getId(),
//                warehouse.getCode(),
//                warehouse.getName()
//        ));
//
//        return new CreateWarehouseUsecaseResponse(
//                warehouse.getId(),
//                supplier.getId(),
//                warehouse.getCode(),
//                warehouse.getName(),
//                warehouse.isActive(),
//                warehouse.getCreatedAt()
//        );
//    }
//
//    private record SupplierWarehouseDeclaredEvent(
//            java.util.UUID warehouseId,
//            java.util.UUID supplierId,
//            String code,
//            String name
//    ) {
//    }
//}
