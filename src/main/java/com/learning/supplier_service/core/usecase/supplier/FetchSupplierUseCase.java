package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.service.supplier.fetch.FetchSupplierService;
import com.learning.supplier_service.core.service.supplier.fetch.FetchSupplierServiceRequest;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

/**
 * Unified use case supporting both single supplier fetch and paginated supplier listing.
 * This use case consolidates the logic for two operations:
 * - Fetching a single supplier by ID
 * - Listing suppliers with pagination
 */
@Component
public class FetchSupplierUseCase implements Usecase<FetchSupplierUsecaseRequest, FetchSupplierUsecaseResponse> {

    private final FetchSupplierService fetchSupplierService;

    public FetchSupplierUseCase(FetchSupplierService fetchSupplierService) {
        this.fetchSupplierService = fetchSupplierService;
    }

    @Override
    public FetchSupplierUsecaseResponse execute(FetchSupplierUsecaseRequest request) {
        var serviceRequest = buildServiceRequest(request);
        var serviceResponse = fetchSupplierService.execute(serviceRequest);
        return buildUsecaseResponse(serviceResponse);
    }

    /**
     * Map UseCase request to Service request
     */
    private FetchSupplierServiceRequest buildServiceRequest(FetchSupplierUsecaseRequest request) {
        if (request.isSingleSupplierFetch()) {
            return FetchSupplierServiceRequest.forSingleSupplier(request.getSupplierId());
        } else if (request.isListRequest()) {
            return FetchSupplierServiceRequest.forList(request.getPageable());
        } else {
            throw new IllegalArgumentException(
                    "Invalid request: must provide either supplierId for single fetch or pageable for list"
            );
        }
    }

    /**
     * Map Service response to UseCase response
     */
    private FetchSupplierUsecaseResponse buildUsecaseResponse(
            com.learning.supplier_service.core.service.supplier.fetch.FetchSupplierServiceResponse serviceResponse) {
        if (serviceResponse.isSingleSupplier()) {
            return FetchSupplierUsecaseResponse.ofSingleSupplier(serviceResponse.getSupplier());
        } else if (serviceResponse.isList()) {
            return FetchSupplierUsecaseResponse.ofList(serviceResponse.getSuppliers());
        } else {
            throw new IllegalStateException("Invalid service response state");
        }
    }
}
