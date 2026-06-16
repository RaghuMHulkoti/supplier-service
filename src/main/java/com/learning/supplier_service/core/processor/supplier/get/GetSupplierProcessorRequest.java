package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Unified request supporting both single supplier fetch and paginated supplier listing.
 * Use supplierId for fetching a single supplier.
 * Use pageable for listing suppliers with pagination.
 */
@Builder
@Getter
public class GetSupplierProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private Pageable pageable;

    /**
     * Creates a request for fetching a single supplier
     */
    public static GetSupplierProcessorRequest forSingleSupplier(UUID supplierId) {
        return GetSupplierProcessorRequest.builder().supplierId(supplierId).build();
    }

    /**
     * Creates a request for listing suppliers with pagination
     */
    public static GetSupplierProcessorRequest forList(Pageable pageable) {
        return GetSupplierProcessorRequest.builder().pageable(pageable).build();
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
