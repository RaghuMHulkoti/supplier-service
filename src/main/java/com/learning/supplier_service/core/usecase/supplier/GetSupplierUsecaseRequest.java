package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.usecase.UsecaseRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class GetSupplierUsecaseRequest implements UsecaseRequest {
    UUID supplierId;
}

