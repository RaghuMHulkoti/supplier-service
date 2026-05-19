package com.learning.supplier_service.core.processor.supplier;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.*;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SupplierProcessor implements Processor<SupplierProcessorRequest, SupplierProcessorResponse> {
    //    private final CreateSupplierUseCase createSupplierUseCase;
    private final SupplierUsecase supplierUsecase;
//    private final SubmitSupplierKycUseCase submitSupplierKycUseCase;
//    private final GetSupplierUseCase getSupplierUseCase;

    public SupplierProcessor(
            SupplierUsecase supplierUsecase
//            SubmitSupplierKycUseCase submitSupplierKycUseCase,
//            GetSupplierUseCase getSupplierUseCase
    ) {
        this.supplierUsecase = supplierUsecase;
//        this.createSupplierUseCase = createSupplierUseCase;
//        this.submitSupplierKycUseCase = submitSupplierKycUseCase;
//        this.getSupplierUseCase = getSupplierUseCase;
    }

    @Override
    public SupplierProcessorResponse process(SupplierProcessorRequest request) {

        SupplierUsecaseResponse supplierUsecaseResponse =
                supplierUsecase.execute(buildSupplierUsecaseRequest(request));

        return Optional.of(supplierUsecaseResponse)
                .map(this::buildSupplierProcessorResponse)
                .orElseThrow();
    }

    private static SupplierUsecaseRequest buildSupplierUsecaseRequest(SupplierProcessorRequest request) {
        return SupplierUsecaseRequest.builder()
                .legalName(request.getLegalName())
                .displayName(request.getDisplayName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .taxId(request.getTaxId())
                .country(request.getCountry())
                .commissionPct(request.getCommissionPct())
                .build();
    }

//    public CreateSupplierUsecaseResponse create(CreateSupplierUsecaseRequest request) {
//        return createSupplierUseCase.execute(request);
//    }

//    public SupplierEntity getRequired(UUID supplierId) {
//        return getSupplierUseCase.execute(new GetSupplierUsecaseRequest(supplierId)).supplier();
//    }

//    public SubmitSupplierKycUsecaseResponse submitKyc(SubmitSupplierKycUsecaseRequest request) {
//        return submitSupplierKycUseCase.execute(request);
//    }
//
//    public SubmitSupplierKycUsecaseResponse submitKyc(UUID supplierId, String documentKind, String documentUrl) {
//        return submitKyc(new SubmitSupplierKycUsecaseRequest(
//                supplierId,
//                documentKind,
//                documentUrl
//        ));
//    }

    private SupplierProcessorResponse buildSupplierProcessorResponse(
            SupplierUsecaseResponse response) {

        return SupplierProcessorResponse.builder()
                .success(response.isSuccess())
                .supplierId(response.getId())
                .build();
    }
}




