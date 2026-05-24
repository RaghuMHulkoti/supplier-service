package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.mysql.outbox.entity.SupplierOutboxEntity;

import java.util.List;

public interface SupplierOutboxGateway {

    List<SupplierOutboxEntity> findAllOrderByIdAsc();

    SupplierOutboxEntity save(SupplierOutboxEntity supplierOutboxEntity);
}

