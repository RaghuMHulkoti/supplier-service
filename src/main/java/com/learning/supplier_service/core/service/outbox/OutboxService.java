package com.learning.supplier_service.core.service.outbox;

import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.gateway.mysql.supplier.repository.SupplierOutboxRepository;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierOutboxEntity;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.UUID;

@Component
public class OutboxService implements Service<OutboxServiceRequest, OutboxServiceResponse> {
    private final SupplierOutboxRepository outboxRepository;
    private final JsonMapper jsonMapper;

    public OutboxService(SupplierOutboxRepository outboxRepository, JsonMapper jsonMapper) {
        this.outboxRepository = outboxRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public OutboxServiceResponse execute(OutboxServiceRequest request) {
        try {
            var serializedPayload = jsonMapper.writeValueAsString(request.getPayload());
            var savedEntity = outboxRepository.save(new SupplierOutboxEntity(
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
