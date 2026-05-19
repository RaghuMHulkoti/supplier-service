//package com.learning.supplier_service.core.processor.outbox;
//
//import com.learning.supplier_service.core.processor.Processor;
//import com.learning.supplier_service.core.service.outbox.OutboxService;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OutboxProcessor
//        implements Processor<OutboxProcessorRequest, OutboxProcessorResponse> {
//
//    private final OutboxService outboxService;
//
//    public OutboxProcessor(OutboxService outboxService) {
//        this.outboxService = outboxService;
//    }
//
//    @Override
//    public OutboxProcessorResponse process(OutboxProcessorRequest request) {
//
//        var serviceRequest = new OutboxServiceRequest(
//                request.getAggregateId(),
//                request.getEventType(),
//                request.getSerializedPayload()
//        );
//
//        var serviceResponse = outboxService.execute(serviceRequest);
//
//        return new OutboxProcessorResponse(
//                serviceResponse.getAggregateId(),
//                serviceResponse.getEventType(),
//                serviceResponse.getStatus()
//        );
//    }
//}
//
