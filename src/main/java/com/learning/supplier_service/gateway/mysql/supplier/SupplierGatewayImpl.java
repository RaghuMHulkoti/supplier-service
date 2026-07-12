package com.learning.supplier_service.gateway.mysql.supplier;

import com.learning.supplier_service.gateway.SupplierGateway;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupplierGatewayImpl implements SupplierGateway {

    private final SupplierRepository supplierRepository;

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return supplierRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Optional<SupplierEntity> getSupplierById(UUID supplierId) {
        return supplierRepository.findById(supplierId);
    }

    @Override
    public Optional<SupplierEntity> findByEmailIgnoreCase(String email) {
        return supplierRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public Page<SupplierEntity> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public SupplierEntity saveSupplier(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
    }
}

