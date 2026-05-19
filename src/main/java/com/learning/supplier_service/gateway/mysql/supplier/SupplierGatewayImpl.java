package com.learning.supplier_service.gateway.mysql.supplier;

import com.learning.supplier_service.gateway.SupplierGateway;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierKycEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierOutboxEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierProductEntity;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierWarehouseEntity;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierKycRepository;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierOutboxRepository;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierProductRepository;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierRepository;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierWarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupplierGatewayImpl implements SupplierGateway {

    private final SupplierRepository supplierRepository;
    private final SupplierKycRepository supplierKycRepository;
    private final SupplierOutboxRepository supplierOutboxRepository;
    private final SupplierProductRepository supplierProductRepository;
    private final SupplierWarehouseRepository supplierWarehouseRepository;

    // Supplier

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return supplierRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Optional<SupplierEntity> findByEmailIgnoreCase(String email) {
        return supplierRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public SupplierEntity saveSupplier(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
    }

    // Supplier Product

    @Override
    public boolean existsBySupplierIdAndProductId(UUID supplierId, UUID productId) {
        return supplierProductRepository.existsBySupplier_IdAndProductId(supplierId, productId);
    }

    @Override
    public boolean existsBySupplierIdAndSupplierSkuIgnoreCase(UUID supplierId,
                                                              String supplierSku) {
        return supplierProductRepository
                .existsBySupplier_IdAndSupplierSkuIgnoreCase(supplierId, supplierSku);
    }

    @Override
    public Optional<SupplierProductEntity> findSupplierProductByIdAndSupplierId(UUID id,
                                                                                UUID supplierId) {
        return supplierProductRepository.findByIdAndSupplier_Id(id, supplierId);
    }

    @Override
    public Page<SupplierProductEntity> findAllSupplierProductsBySupplierId(UUID supplierId,
                                                                           Pageable pageable) {
        return supplierProductRepository.findAllBySupplier_Id(supplierId, pageable);
    }

    @Override
    public List<SupplierProductEntity> findAllByProductIdAndActiveTrueOrderByPriceAsc(
            UUID productId) {
        return supplierProductRepository
                .findAllByProductIdAndActiveTrueOrderByPriceAsc(productId);
    }

    @Override
    public SupplierProductEntity saveSupplierProduct(
            SupplierProductEntity supplierProductEntity) {
        return supplierProductRepository.save(supplierProductEntity);
    }

    // Supplier Warehouse

    @Override
    public boolean existsWarehouseBySupplierIdAndCodeIgnoreCase(UUID supplierId,
                                                                String code) {
        return supplierWarehouseRepository
                .existsBySupplier_IdAndCodeIgnoreCase(supplierId, code);
    }

    @Override
    public List<SupplierWarehouseEntity> findAllWarehousesBySupplierIdOrderByCreatedAtAsc(
            UUID supplierId) {
        return supplierWarehouseRepository
                .findAllBySupplier_IdOrderByCreatedAtAsc(supplierId);
    }

    @Override
    public SupplierWarehouseEntity saveSupplierWarehouse(
            SupplierWarehouseEntity supplierWarehouseEntity) {
        return supplierWarehouseRepository.save(supplierWarehouseEntity);
    }

    // Supplier KYC

    @Override
    public SupplierKycEntity saveSupplierKyc(SupplierKycEntity supplierKycEntity) {
        return supplierKycRepository.save(supplierKycEntity);
    }

    // Supplier Outbox

    @Override
    public List<SupplierOutboxEntity> findAllOutboxOrderByIdAsc() {
        return supplierOutboxRepository.findAllByOrderByIdAsc();
    }

    @Override
    public SupplierOutboxEntity saveSupplierOutbox(
            SupplierOutboxEntity supplierOutboxEntity) {
        return supplierOutboxRepository.save(supplierOutboxEntity);
    }
}