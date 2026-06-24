package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Builder
@Getter
public class GetSupplierProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private Pageable pageable;

    public static GetSupplierProcessorRequest forSingleSupplier(UUID supplierId) {
        return GetSupplierProcessorRequest.builder().supplierId(supplierId).build();
    }


    public static GetSupplierProcessorRequest forList(Pageable pageable) {
        return GetSupplierProcessorRequest.builder().pageable(pageable).build();
    }


    public boolean isSingleSupplierFetch() {
        return supplierId != null && pageable == null;
    }


    public boolean isListRequest() {
        return pageable != null && supplierId == null;
    }
}
