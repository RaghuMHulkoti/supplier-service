package com.learning.supplier_service.gateway.mysql.supplier.repository;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierKycEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierKycRepository extends JpaRepository<SupplierKycEntity, UUID> {
}
