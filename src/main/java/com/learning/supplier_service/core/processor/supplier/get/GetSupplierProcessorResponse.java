package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.common.StatusCode;
import com.learning.supplier_service.core.domain.Supplier;
import com.learning.supplier_service.core.processor.ProcessorResponse;
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
public class GetSupplierProcessorResponse implements ProcessorResponse {
    private Supplier supplier;
    private Page<Supplier> suppliers;
    private StatusCode statusCode;;


    /**
     * Creates a response for a single supplier fetch
     */
    public static GetSupplierProcessorResponse ofSingleSupplier(Supplier supplier) {
        return GetSupplierProcessorResponse.builder().supplier(supplier)
                .statusCode(StatusCode.SUCCESS).build();
    }

    /**
     * Creates a response for paginated supplier list
     */
    public static GetSupplierProcessorResponse ofList(Page<Supplier> suppliers) {
        return GetSupplierProcessorResponse.builder().suppliers(suppliers)
                .statusCode(StatusCode.SUCCESS).build();
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
