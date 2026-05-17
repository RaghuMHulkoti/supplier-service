package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<SupplierEntity> findByEmailIgnoreCase(String email);
}
