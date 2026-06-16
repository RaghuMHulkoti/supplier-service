package com.learning.supplier_service.core.service.supplier.fetch;

import com.learning.supplier_service.core.service.ServiceRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Unified request for both single supplier fetch and paginated supplier listing.
 * Use supplierId for fetching a single supplier.
 * Use pageable for listing suppliers with pagination.
 */
@Builder
@Getter
public class FetchSupplierServiceRequest implements ServiceRequest {
    private UUID supplierId;
    private Pageable pageable;

    /**
     * Creates a request for fetching a single supplier
     */
    public static FetchSupplierServiceRequest forSingleSupplier(UUID supplierId) {
        return FetchSupplierServiceRequest.builder().supplierId(supplierId).build();
    }

    /**
     * Creates a request for listing suppliers with pagination
     */
    public static FetchSupplierServiceRequest forList(Pageable pageable) {
        return FetchSupplierServiceRequest.builder().pageable(pageable).build();
    }

    /**
     * Check if this is a single supplier fetch request
     */
    public boolean isSingleSupplierFetch() {
        return supplierId != null && pageable == null;
    }

    /**
     * Check if this is a list request
     */
    public boolean isListRequest() {
        return pageable != null && supplierId == null;
    }
}

