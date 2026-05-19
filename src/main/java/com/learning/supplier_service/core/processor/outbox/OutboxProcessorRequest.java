package com.learning.supplier_service.core.processor.outbox;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public class OutboxProcessorRequest implements ProcessorRequest {
    private final UUID aggregateId;
    private final String eventType;
    private final String serializedPayload;

    public OutboxProcessorRequest(UUID aggregateId, String eventType, String serializedPayload) {
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.serializedPayload = serializedPayload;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getSerializedPayload() {
        return serializedPayload;
    }
}

