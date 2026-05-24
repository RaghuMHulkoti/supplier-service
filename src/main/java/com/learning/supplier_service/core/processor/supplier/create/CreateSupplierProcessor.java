package com.learning.supplier_service.core.processor.supplier.create;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.create.CreateSupplierUsecase;
import com.learning.supplier_service.core.usecase.supplier.create.CreateSupplierUsecaseRequest;
import com.learning.supplier_service.core.usecase.supplier.create.CreateSupplierUsecaseResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateSupplierProcessor implements Processor<CreateSupplierProcessorRequest, CreateSupplierProcessorResponse> {
    //    private final CreateSupplierUseCase createSupplierUseCase;
    private final CreateSupplierUsecase createSupplierUsecase;
//    private final SubmitSupplierKycUseCase submitSupplierKycUseCase;
//    private final GetSupplierUseCase getSupplierUseCase;

    public CreateSupplierProcessor(
            CreateSupplierUsecase createSupplierUsecase
//            SubmitSupplierKycUseCase submitSupplierKycUseCase,
//            GetSupplierUseCase getSupplierUseCase
    ) {
        this.createSupplierUsecase = createSupplierUsecase;
//        this.createSupplierUseCase = createSupplierUseCase;
//        this.submitSupplierKycUseCase = submitSupplierKycUseCase;
//        this.getSupplierUseCase = getSupplierUseCase;
    }

    @Override
    public CreateSupplierProcessorResponse process(CreateSupplierProcessorRequest request) {

        CreateSupplierUsecaseResponse createSupplierUsecaseResponse =
                createSupplierUsecase.execute(buildSupplierUsecaseRequest(request));

        return Optional.of(createSupplierUsecaseResponse)
                .map(this::buildSupplierProcessorResponse)
                .orElseThrow();
    }

    private static CreateSupplierUsecaseRequest buildSupplierUsecaseRequest(CreateSupplierProcessorRequest request) {
        return CreateSupplierUsecaseRequest.builder()
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

    private CreateSupplierProcessorResponse buildSupplierProcessorResponse(
            CreateSupplierUsecaseResponse response) {

        return CreateSupplierProcessorResponse.builder()
                .success(response.isSuccess())
                .supplierId(response.getId())
                .build();
    }
}




