package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.core.usecase.supplier.FetchSupplierUseCase;
import com.learning.supplier_service.core.usecase.supplier.FetchSupplierUsecaseRequest;
import com.learning.supplier_service.core.processor.Processor;
import org.springframework.stereotype.Component;

/**
 * Unified processor supporting both single supplier fetch and paginated supplier listing.
 * This processor consolidates the logic for two use cases:
 * - Fetching a single supplier by ID using FetchSupplierUseCase
 * - Listing suppliers with pagination using FetchSupplierUseCase
 */
@Component
public class FetchSupplierProcessor implements Processor<GetSupplierProcessorRequest, GetSupplierProcessorResponse> {

    private final FetchSupplierUseCase fetchSupplierUseCase;

    public FetchSupplierProcessor(FetchSupplierUseCase fetchSupplierUseCase) {
        this.fetchSupplierUseCase = fetchSupplierUseCase;
    }

    @Override
    public GetSupplierProcessorResponse process(GetSupplierProcessorRequest request) {
        var usecaseRequest = buildUsecaseRequest(request);
        var usecaseResponse = fetchSupplierUseCase.execute(usecaseRequest);
        return buildProcessorResponse(usecaseResponse);
    }

    /**
     * Map Processor request to UseCase request
     */
    private FetchSupplierUsecaseRequest buildUsecaseRequest(GetSupplierProcessorRequest request) {
        if (request.isSingleSupplierFetch()) {
            return FetchSupplierUsecaseRequest.forSingleSupplier(request.getSupplierId());
        } else if (request.isListRequest()) {
            return FetchSupplierUsecaseRequest.forList(request.getPageable());
        } else {
            throw new IllegalArgumentException(
                    "Invalid request: must provide either supplierId for single fetch or pageable for list"
            );
        }
    }

    /**
     * Map UseCase response to Processor response
     */
    private GetSupplierProcessorResponse buildProcessorResponse(
            com.learning.supplier_service.core.usecase.supplier.FetchSupplierUsecaseResponse usecaseResponse) {
        if (usecaseResponse.isSingleSupplier()) {
            return GetSupplierProcessorResponse.ofSingleSupplier(usecaseResponse.getSupplier());
        } else if (usecaseResponse.isList()) {
            return GetSupplierProcessorResponse.ofList(usecaseResponse.getSuppliers());
        } else {
            throw new IllegalStateException("Invalid usecase response state");
        }
    }
}
