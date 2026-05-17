package com.learning.supplier_service.core.service.supplier;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.exception.DuplicateSupplierEmailException;
import com.learning.supplier_service.exception.SupplierNotFoundException;
import com.learning.supplier_service.gateway.SupplierKycRepository;
import com.learning.supplier_service.gateway.SupplierRepository;
import com.learning.supplier_service.gateway.entity.SupplierEntity;
import com.learning.supplier_service.gateway.entity.SupplierKycEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;

@Component
public class SupplierService implements Service<SupplierServiceRequest, SupplierServiceResponse> {
    private final SupplierRepository supplierRepository;
    private final SupplierKycRepository supplierKycRepository;

    public SupplierService(SupplierRepository supplierRepository, SupplierKycRepository supplierKycRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierKycRepository = supplierKycRepository;
    }


    @Override
    public SupplierServiceResponse execute(SupplierServiceRequest request) {
        if (supplierRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateSupplierEmailException(request.getEmail());
        }

        var supplier = buildSupplierEntity(request);
        var savedSupplier = supplierRepository.save(supplier);
        return buildSupplierResponse(savedSupplier);

    }

    private static SupplierEntity buildSupplierEntity(SupplierServiceRequest request) {
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

    public SupplierEntity getRequired(UUID supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
    }

    public SupplierKycEntity submitKyc(UUID supplierId, String documentKind, String documentUrl) {
        var supplier = getRequired(supplierId);
        supplier.markKycSubmitted();
        supplierRepository.save(supplier);

        var kyc = supplierKycRepository.findById(supplierId)
                .map(existing -> {
                    existing.replaceDocument(documentKind, documentUrl);
                    return existing;
                })
                .orElseGet(() -> new SupplierKycEntity(supplier, documentKind, documentUrl));

        return supplierKycRepository.save(kyc);
    }

    private static SupplierServiceResponse buildSupplierResponse(SupplierEntity supplier) {
        return SupplierServiceResponse.builder()
                .id(supplier.getId())
                .build();
    }
}
