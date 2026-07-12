package com.learning.supplier_service.web.mapper;

import com.learning.supplier_service.core.processor.supplier.create.CreateSupplierProcessorResponse;
import com.learning.supplier_service.core.processor.supplier.get.GetSupplierProcessorResponse;
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

    public static Response<Object> mapSupplierGetResponse(GetSupplierProcessorResponse response){
        return new Response<>(
                response.getStatusCode(),
                "Supplier Fetched successfully",
                buildGetSupplier(response)
        );

    }
     public static Object buildGetSupplier(GetSupplierProcessorResponse response){
         if (response.isList()) {
            return response.getSuppliers();
         } else {
             return response.getSupplier();
         }
     }
}
