//package com.learning.supplier_service.core.processor.warehouse;
//
//import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUseCase;
//import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUsecaseRequest;
//import com.learning.supplier_service.core.usecase.warehouse.CreateWarehouseUsecaseResponse;
//import com.learning.supplier_service.core.usecase.warehouse.ListWarehouseUseCase;
//import com.learning.supplier_service.core.usecase.warehouse.ListWarehouseUsecaseRequest;
//import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierWarehouseEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.UUID;
//
//@Component
//public class WarehouseProcessor {
//    private final CreateWarehouseUseCase createWarehouseUseCase;
//    private final ListWarehouseUseCase listWarehouseUseCase;
//
//    public WarehouseProcessor(CreateWarehouseUseCase createWarehouseUseCase, ListWarehouseUseCase listWarehouseUseCase) {
//        this.createWarehouseUseCase = createWarehouseUseCase;
//        this.listWarehouseUseCase = listWarehouseUseCase;
//    }
//
//    public CreateWarehouseUsecaseResponse create(CreateWarehouseUsecaseRequest request) {
//        return createWarehouseUseCase.execute(request);
//    }
//
//    public List<SupplierWarehouseEntity> list(UUID supplierId) {
//        return listWarehouseUseCase.execute(new ListWarehouseUsecaseRequest(supplierId)).warehouses();
//    }
//}
//
