package com.learning.supplier_service.core.service.supplier.fetch;

import com.learning.supplier_service.core.service.ServiceResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

/**
 * Unified response supporting both single supplier and paginated supplier listing.
 * For single supplier fetch: supplier field contains the supplier
 * For list: suppliers field contains the paginated suppliers
 */
@Builder
@Getter
public class FetchSupplierServiceResponse implements ServiceResponse {
    private SupplierEntity supplier;
    private Page<SupplierEntity> suppliers;

    /**
     * Creates a response for a single supplier fetch
     */
    public static FetchSupplierServiceResponse ofSingleSupplier(SupplierEntity supplier) {
        return FetchSupplierServiceResponse.builder().supplier(supplier).build();
    }

    /**
     * Creates a response for paginated supplier list
     */
    public static FetchSupplierServiceResponse ofList(Page<SupplierEntity> suppliers) {
        return FetchSupplierServiceResponse.builder().suppliers(suppliers).build();
    }

    /**
     * Check if this is a single supplier response
     */
    public boolean isSingleSupplier() {
        return supplier != null && suppliers == null;
    }

    /**
     * Check if this is a list response
     */
    public boolean isList() {
        return suppliers != null && supplier == null;
    }
}

