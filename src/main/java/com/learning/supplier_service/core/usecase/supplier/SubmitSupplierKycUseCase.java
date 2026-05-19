//package com.learning.supplier_service.core.usecase.supplier;
//
//import com.learning.supplier_service.core.service.outbox.OutboxService;
//import com.learning.supplier_service.core.service.supplier.SupplierService;
//import com.learning.supplier_service.core.usecase.Usecase;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class SubmitSupplierKycUseCase implements Usecase<SubmitSupplierKycUsecaseRequest, SubmitSupplierKycUsecaseResponse> {
//    private final SupplierService supplierService;
//    private final OutboxService outboxService;
//
//    public SubmitSupplierKycUseCase(SupplierService supplierService, OutboxService outboxService) {
//        this.supplierService = supplierService;
//        this.outboxService = outboxService;
//    }
//
//    @Override
//    @Transactional
//    public SubmitSupplierKycUsecaseResponse execute(SubmitSupplierKycUsecaseRequest request) {
//        var kyc = supplierService.submitKyc(
//                request.supplierId(),
//                request.documentKind(),
//                request.documentUrl()
//        );
//        var supplier = supplierService.getRequired(request.supplierId());
//
//        outboxService.append(supplier.getId(), "SUPPLIER_KYC_SUBMITTED", new SupplierKycSubmittedEvent(
//                supplier.getId(),
//                kyc.getDocumentKind()
//        ));
//
//        return new SubmitSupplierKycUsecaseResponse(
//                supplier.getId(),
//                kyc.getDocumentKind(),
//                kyc.getDocumentUrl(),
//                supplier.getOnboardingStatus()
//        );
//    }
//
//    private record SupplierKycSubmittedEvent(
//            java.util.UUID supplierId,
//            String documentKind
//    ) {
//    }
//}
