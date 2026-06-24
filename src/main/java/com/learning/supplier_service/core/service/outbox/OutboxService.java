package com.learning.supplier_service.core.service.outbox;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.gateway.SupplierOutboxGateway;
import com.learning.supplier_service.gateway.mysql.outbox.entity.SupplierOutboxEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OutboxService implements Service<OutboxServiceRequest, OutboxServiceResponse> {
    private final SupplierOutboxGateway supplierOutboxGateway;
    private final ObjectMapper objectMapper;

    public OutboxService(SupplierOutboxGateway supplierOutboxGateway, ObjectMapper objectMapper) {
        this.supplierOutboxGateway = supplierOutboxGateway;
        this.objectMapper = objectMapper;
    }

    @Override
    public OutboxServiceResponse execute(OutboxServiceRequest request) {
        try {
            var serializedPayload = objectMapper.writeValueAsString(request.getPayload());
            var savedEntity = supplierOutboxGateway.save(new SupplierOutboxEntity(
                    request.getAggregateId(),
                    request.getEventType(),
                    serializedPayload
            ));

            return new OutboxServiceResponse(
                    savedEntity.getAggregateId(),
                    savedEntity.getEventType(),
                    savedEntity.getStatus()
            );
        } catch (JacksonException ex) {
            throw new IllegalStateException("Failed to serialize outbox payload", ex);
        }
    }

    public void append(UUID aggregateId, String eventType, Object payload) {
        execute(new OutboxServiceRequest(aggregateId, eventType, payload));
    }
}
