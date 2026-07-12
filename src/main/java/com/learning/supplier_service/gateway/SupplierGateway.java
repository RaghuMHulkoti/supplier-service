package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface SupplierGateway {

    boolean existsByEmailIgnoreCase(String email);

    Optional<SupplierEntity> getSupplierById(UUID supplierId);

    Optional<SupplierEntity> findByEmailIgnoreCase(String email);

    Page<SupplierEntity> findAll(Pageable pageable);

    SupplierEntity saveSupplier(SupplierEntity supplierEntity);
}