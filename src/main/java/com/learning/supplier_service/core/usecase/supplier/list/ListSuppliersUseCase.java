package com.learning.supplier_service.core.usecase.supplier.list;

import com.learning.supplier_service.core.service.supplier.fetch.FetchSupplierService;
import com.learning.supplier_service.core.service.supplier.fetch.FetchSupplierServiceRequest;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

/**
 * Use case for listing suppliers with pagination.
 * This use case delegates to the unified FetchSupplierService which handles both
 * single supplier fetch and pagination scenarios.
 * 
 * Note: This is now a wrapper around FetchSupplierUseCase for backward compatibility.
 * New code should use FetchSupplierUseCase directly.
 */
@Component
public class ListSuppliersUseCase implements Usecase<ListSuppliersUsecaseRequest, ListSuppliersUsecaseResponse> {

    private final FetchSupplierService fetchSupplierService;

    public ListSuppliersUseCase(FetchSupplierService fetchSupplierService) {
        this.fetchSupplierService = fetchSupplierService;
    }

    @Override
    public ListSuppliersUsecaseResponse execute(ListSuppliersUsecaseRequest request) {
        var serviceRequest = FetchSupplierServiceRequest.forList(request.pageable());
        var serviceResponse = fetchSupplierService.execute(serviceRequest);
        return new ListSuppliersUsecaseResponse(serviceResponse.getSuppliers());
    }
}
