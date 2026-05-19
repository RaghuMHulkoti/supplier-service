//package com.learning.supplier_service.core.usecase.warehouse;
//
//import com.learning.supplier_service.core.usecase.Usecase;
//
//public class WarehouseUsecase implements Usecase<WarehouseUsecaseRequest, WarehouseUsecaseResponse> {
//    private final WarehouseUsecaseHandler handler;
//
//    public WarehouseUsecase(WarehouseUsecaseHandler handler) {
//        this.handler = handler;
//    }
//
//    @Override
//    public WarehouseUsecaseResponse execute(WarehouseUsecaseRequest request) {
//        return handler.handle(request);
//    }
//}
