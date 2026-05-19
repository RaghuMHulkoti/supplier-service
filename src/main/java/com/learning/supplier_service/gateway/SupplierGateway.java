package com.learning.supplier_service.gateway;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierKycEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierOutboxEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierProductEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierWarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierGateway {

    // Supplier
    boolean existsByEmailIgnoreCase(String email);

    Optional<SupplierEntity> findByEmailIgnoreCase(String email);

    SupplierEntity saveSupplier(SupplierEntity supplierEntity);

    // Supplier Product
    boolean existsBySupplierIdAndProductId(UUID supplierId, UUID productId);

    boolean existsBySupplierIdAndSupplierSkuIgnoreCase(UUID supplierId, String supplierSku);

    Optional<SupplierProductEntity> findSupplierProductByIdAndSupplierId(UUID id, UUID supplierId);

    Page<SupplierProductEntity> findAllSupplierProductsBySupplierId(UUID supplierId,
                                                                    Pageable pageable);

    List<SupplierProductEntity> findAllByProductIdAndActiveTrueOrderByPriceAsc(UUID productId);

    SupplierProductEntity saveSupplierProduct(SupplierProductEntity supplierProductEntity);

    // Supplier Warehouse
    boolean existsWarehouseBySupplierIdAndCodeIgnoreCase(UUID supplierId, String code);

    List<SupplierWarehouseEntity> findAllWarehousesBySupplierIdOrderByCreatedAtAsc(UUID supplierId);

    SupplierWarehouseEntity saveSupplierWarehouse(SupplierWarehouseEntity supplierWarehouseEntity);

    // Supplier KYC
    SupplierKycEntity saveSupplierKyc(SupplierKycEntity supplierKycEntity);

    // Supplier Outbox
    List<SupplierOutboxEntity> findAllOutboxOrderByIdAsc();

    SupplierOutboxEntity saveSupplierOutbox(SupplierOutboxEntity supplierOutboxEntity);
}