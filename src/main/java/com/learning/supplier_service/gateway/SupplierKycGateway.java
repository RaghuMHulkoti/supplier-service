package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.mysql.kyc.entity.SupplierKycEntity;

import java.util.Optional;
import java.util.UUID;

public interface SupplierKycGateway {

    SupplierKycEntity save(SupplierKycEntity supplierKycEntity);

    Optional<SupplierKycEntity> findBySupplierId(UUID supplierId);
}
