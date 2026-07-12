package com.learning.supplier_service.core.service.supplier.fetch;

import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.exception.SupplierNotFoundException;
import com.learning.supplier_service.gateway.SupplierGateway;
import org.springframework.stereotype.Component;

/**
 * Unified service supporting both single supplier fetch and paginated supplier listing.
 * This service consolidates the logic for two operations:
 * - Fetching a single supplier by ID
 * - Listing suppliers with pagination
 */
@Component
public class FetchSupplierService implements Service<FetchSupplierServiceRequest, FetchSupplierServiceResponse> {

    private final SupplierGateway supplierGateway;

    public FetchSupplierService(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public FetchSupplierServiceResponse execute(FetchSupplierServiceRequest request) {
        if (request.isSingleSupplierFetch()) {
            return processSingleSupplierFetch(request);
        } else if (request.isListRequest()) {
            return processSupplierList(request);
        } else {
            throw new IllegalArgumentException(
                    "Invalid request: must provide either supplierId for single fetch or pageable for list"
            );
        }
    }

    /**
     * Process single supplier fetch request
     */
    private FetchSupplierServiceResponse processSingleSupplierFetch(FetchSupplierServiceRequest request) {
        var supplier = supplierGateway.getSupplierById(request.getSupplierId())
                .orElseThrow(() -> new SupplierNotFoundException(request.getSupplierId()));
        return FetchSupplierServiceResponse.ofSingleSupplier(supplier);
    }

    /**
     * Process supplier list request with pagination
     */
    private FetchSupplierServiceResponse processSupplierList(FetchSupplierServiceRequest request) {
        var suppliers = supplierGateway.findAll(request.getPageable());
        return FetchSupplierServiceResponse.ofList(suppliers);
    }
}

