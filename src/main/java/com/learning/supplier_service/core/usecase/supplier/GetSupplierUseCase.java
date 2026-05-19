//package com.learning.supplier_service.core.usecase.supplier;
//
//import com.learning.supplier_service.core.service.supplier.SupplierService;
//import com.learning.supplier_service.core.usecase.Usecase;
//import org.springframework.stereotype.Component;
//
//@Component
//public class GetSupplierUseCase implements Usecase<GetSupplierUsecaseRequest, GetSupplierUsecaseResponse> {
//    private final SupplierService supplierService;
//
//    public GetSupplierUseCase(SupplierService supplierService) {
//        this.supplierService = supplierService;
//    }
//
//    @Override
//    public GetSupplierUsecaseResponse execute(GetSupplierUsecaseRequest request) {
//        return new GetSupplierUsecaseResponse(supplierService.getRequired(request.supplierId()));
//    }
//}
//
