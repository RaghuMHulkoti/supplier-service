package com.learning.supplier_service.core.processor.outbox;

import com.learning.supplier_service.core.processor.ProcessorResponse;

import java.util.UUID;

public class OutboxProcessorResponse implements ProcessorResponse {
    private final UUID aggregateId;
    private final String eventType;
    private final String status;

    public OutboxProcessorResponse(UUID aggregateId, String eventType, String status) {
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.status = status;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getStatus() {
        return status;
    }
}

