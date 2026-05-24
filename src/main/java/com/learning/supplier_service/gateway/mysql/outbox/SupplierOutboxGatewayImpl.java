package com.learning.supplier_service.gateway.mysql.outbox;

import com.learning.supplier_service.gateway.mysql.outbox.entity.SupplierOutboxEntity;
import com.learning.supplier_service.gateway.mysql.outbox.repository.SupplierOutboxRepository;
import com.learning.supplier_service.gateway.SupplierOutboxGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SupplierOutboxGatewayImpl implements SupplierOutboxGateway {

    private final SupplierOutboxRepository supplierOutboxRepository;

    @Override
    public List<SupplierOutboxEntity> findAllOrderByIdAsc() {
        return supplierOutboxRepository.findAllByOrderByIdAsc();
    }

    @Override
    public SupplierOutboxEntity save(SupplierOutboxEntity supplierOutboxEntity) {
        return supplierOutboxRepository.save(supplierOutboxEntity);
    }
}

