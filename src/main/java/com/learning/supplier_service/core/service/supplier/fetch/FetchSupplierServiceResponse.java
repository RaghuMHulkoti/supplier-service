package com.learning.supplier_service.core.service.supplier.fetch;

import com.learning.supplier_service.common.StatusCode;
import com.learning.supplier_service.core.domain.Supplier;
import com.learning.supplier_service.core.service.ServiceResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.mapper.SupplierDomainMapper;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;


@Builder
@Getter
public class FetchSupplierServiceResponse implements ServiceResponse {

    private Supplier supplier;
    private Page<Supplier> suppliers;
    private StatusCode statusCode;

    public static FetchSupplierServiceResponse ofSingleSupplier(SupplierEntity supplier) {
        return FetchSupplierServiceResponse.builder()
                .supplier(SupplierDomainMapper.toDomain(supplier))
                .statusCode(StatusCode.SUCCESS)
                .build();
    }

    public static FetchSupplierServiceResponse ofList(Page<SupplierEntity> suppliers) {
        return FetchSupplierServiceResponse.builder()
                .suppliers(
                        suppliers.map(SupplierDomainMapper::toDomain)
                )
                .statusCode(StatusCode.SUCCESS)
                .build();
    }

    public boolean isSingleSupplier() {
        return supplier != null && suppliers == null;
    }

    public boolean isList() {
        return suppliers != null && supplier == null;
    }
}
