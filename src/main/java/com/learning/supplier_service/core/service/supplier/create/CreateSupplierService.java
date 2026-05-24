package com.learning.supplier_service.core.service.supplier.create;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.exception.DuplicateSupplierEmailException;
import com.learning.supplier_service.exception.SupplierNotFoundException;
import com.learning.supplier_service.gateway.SupplierGateway;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Component
public class CreateSupplierService implements Service<CreateSupplierServiceRequest, CreateSupplierServiceResponse> {

    private final SupplierGateway supplierGateway;

    public CreateSupplierService(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }


    @Override
    public CreateSupplierServiceResponse execute(CreateSupplierServiceRequest request) {
        if (supplierGateway.existsByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateSupplierEmailException(request.getEmail());
        }

        return Optional.of(request)
                .map(this::buildSupplierEntity)
                .map(supplierGateway::saveSupplier)
                .map(this::buildSupplierResponse)
                .orElseThrow();

    }

    private SupplierEntity buildSupplierEntity(CreateSupplierServiceRequest request) {
        return new SupplierEntity(
                UUID.randomUUID(),
                request.getLegalName(),
                request.getDisplayName(),
                request.getEmail().toLowerCase(Locale.ROOT),
                request.getPhone(),
                request.getTaxId(),
                request.getCountry().toUpperCase(Locale.ROOT),
                OnboardingStatus.PENDING,
                BigDecimal.ZERO,
                request.getCommissionPct()
        );
    }

//    public SupplierEntity getRequired(UUID supplierId) {
//        return supplierRepository.findById(supplierId)
//                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
//    }

    public SupplierEntity getRequired(UUID supplierId) {
        return supplierGateway.getSupplierById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
    }

    public SupplierEntity save(SupplierEntity supplier) {
        return supplierGateway.saveSupplier(supplier);
    }

    private CreateSupplierServiceResponse buildSupplierResponse(SupplierEntity supplier) {
        return CreateSupplierServiceResponse.builder()
                .id(supplier.getId())
                .success(Boolean.TRUE)
                .build();
    }
}
