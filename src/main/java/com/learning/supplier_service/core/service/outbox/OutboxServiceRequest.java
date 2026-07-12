package com.learning.supplier_service.core.service.outbox;

import com.learning.supplier_service.core.service.ServiceRequest;

import java.util.UUID;

public class OutboxServiceRequest implements ServiceRequest {
    private final UUID aggregateId;
    private final String eventType;
    private final Object payload;

    public OutboxServiceRequest(UUID aggregateId, String eventType, Object payload) {
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public String getEventType() {
        return eventType;
    }

    public Object getPayload() {
        return payload;
    }
}
