package com.learning.supplier_service.gateway.mysql.supplier.repository;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierOutboxRepository extends JpaRepository<SupplierOutboxEntity, Long> {
    List<SupplierOutboxEntity> findAllByOrderByIdAsc();
}
