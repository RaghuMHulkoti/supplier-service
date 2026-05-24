package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.processor.offer.get.GetOfferProcessor;
import com.learning.supplier_service.core.processor.offer.get.GetOfferProcessorRequest;
import com.learning.supplier_service.core.processor.offer.listbyproduct.ListActiveOffersByProductProcessor;
import com.learning.supplier_service.core.processor.offer.listbyproduct.ListActiveOffersByProductProcessorRequest;
import com.learning.supplier_service.web.response.OfferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/internal/offers")
public class InternalOfferController {

    private final GetOfferProcessor getOfferProcessor;
    private final ListActiveOffersByProductProcessor listActiveOffersByProductProcessor;

    public InternalOfferController(
            GetOfferProcessor getOfferProcessor,
            ListActiveOffersByProductProcessor listActiveOffersByProductProcessor
    ) {
        this.getOfferProcessor = getOfferProcessor;
        this.listActiveOffersByProductProcessor = listActiveOffersByProductProcessor;
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponse> getOffer(@PathVariable UUID offerId) {
        var response = getOfferProcessor.process(GetOfferProcessorRequest.byId(offerId));
        return ResponseEntity.ok(OfferResponse.from(response.offer()));
    }

    @GetMapping
    public ResponseEntity<List<OfferResponse>> listOffersByProduct(@RequestParam UUID productId) {
        var response = listActiveOffersByProductProcessor.process(
                new ListActiveOffersByProductProcessorRequest(productId));
        return ResponseEntity.ok(response.offers().stream().map(OfferResponse::from).toList());
    }
}
