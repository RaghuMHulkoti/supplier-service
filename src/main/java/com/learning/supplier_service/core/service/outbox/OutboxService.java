package com.learning.supplier_service.core.service.outbox;

import com.learning.supplier_service.gateway.SupplierOutboxRepository;
import com.learning.supplier_service.gateway.entity.SupplierOutboxEntity;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import java.util.UUID;

@Service
public class OutboxService {
    private final SupplierOutboxRepository outboxRepository;
    private final JsonMapper jsonMapper;

    public OutboxService(SupplierOutboxRepository outboxRepository, JsonMapper jsonMapper) {
        this.outboxRepository = outboxRepository;
        this.jsonMapper = jsonMapper;
    }

    public void append(UUID aggregateId, String eventType, Object payload) {
        try {
            var serializedPayload = jsonMapper.writeValueAsString(payload);
            outboxRepository.save(new SupplierOutboxEntity(aggregateId, eventType, serializedPayload));
        } catch (JacksonException ex) {
            throw new IllegalStateException("Failed to serialize outbox payload", ex);
        }
    }
}
