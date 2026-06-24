package com.learning.supplier_service.web.mapper;

import com.learning.supplier_service.core.processor.supplier.create.CreateSupplierProcessorResponse;
import com.learning.supplier_service.web.response.Response;

public class SupplierMapper {

    public static Response<Object> mapSupplierCreateResponse(
            CreateSupplierProcessorResponse response
    ) {
        return new Response<>(
                response.getStatusCode(),
                "Supplier created successfully",
                response.getSupplierId()
        );
    }


}
