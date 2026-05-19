//package com.learning.supplier_service.web.controller;
//
//import com.learning.supplier_service.core.processor.offer.OfferProcessor;
//import com.learning.supplier_service.web.response.OfferResponse;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/v1/internal/offers")
//public class InternalOfferController {
//    private final OfferProcessor offerProcessor;
//
//    public InternalOfferController(OfferProcessor offerProcessor) {
//        this.offerProcessor = offerProcessor;
//    }
//
//    @GetMapping("/{offerId}")
//    public OfferResponse getOffer(@PathVariable UUID offerId) {
//        return OfferResponse.from(offerProcessor.get(offerId));
//    }
//
//    @GetMapping
//    public List<OfferResponse> listOffersByProduct(@RequestParam UUID productId) {
//        return offerProcessor.listActiveByProduct(productId).stream()
//                .map(OfferResponse::from)
//                .toList();
//    }
//}
