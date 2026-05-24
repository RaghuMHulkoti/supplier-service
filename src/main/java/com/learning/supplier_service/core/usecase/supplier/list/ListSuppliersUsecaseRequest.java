package com.learning.supplier_service.core.usecase.supplier.list;

import com.learning.supplier_service.core.usecase.UsecaseRequest;
import org.springframework.data.domain.Pageable;

public record ListSuppliersUsecaseRequest(Pageable pageable) implements UsecaseRequest {
}
