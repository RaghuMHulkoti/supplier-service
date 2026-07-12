package com.learning.supplier_service.gateway.mysql.kyc;

import com.learning.supplier_service.gateway.SupplierKycGateway;
import com.learning.supplier_service.gateway.mysql.kyc.entity.SupplierKycEntity;
import com.learning.supplier_service.gateway.mysql.kyc.repository.SupplierKycRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupplierKycGatewayImpl implements SupplierKycGateway {

    private final SupplierKycRepository supplierKycRepository;

    @Override
    public SupplierKycEntity save(SupplierKycEntity supplierKycEntity) {
        return supplierKycRepository.save(supplierKycEntity);
    }

    @Override
    public Optional<SupplierKycEntity> findBySupplierId(UUID supplierId) {
        return supplierKycRepository.findById(supplierId);
    }
}

