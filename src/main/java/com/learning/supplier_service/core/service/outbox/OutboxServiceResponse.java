package com.learning.supplier_service.core.service.outbox;

import com.learning.supplier_service.core.service.ServiceResponse;

import java.util.UUID;

public class OutboxServiceResponse implements ServiceResponse {
    private final UUID aggregateId;
    private final String eventType;
    private final String status;

    public OutboxServiceResponse(UUID aggregateId, String eventType, String status) {
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
