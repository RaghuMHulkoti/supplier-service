package com.learning.supplier_service.core.usecase.kyc.get;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record GetKycUsecaseRequest(UUID supplierId) implements UsecaseRequest {
}
