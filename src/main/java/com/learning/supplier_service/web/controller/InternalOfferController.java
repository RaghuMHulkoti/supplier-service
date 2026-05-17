package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.service.offer.OfferService;
import com.learning.supplier_service.web.response.OfferResponse;
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
    private final OfferService offerService;

    public InternalOfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{offerId}")
    public OfferResponse getOffer(@PathVariable UUID offerId) {
        return OfferResponse.from(offerService.getRequired(offerId));
    }

    @GetMapping
    public List<OfferResponse> listOffersByProduct(@RequestParam UUID productId) {
        return offerService.listActiveByProduct(productId).stream()
                .map(OfferResponse::from)
                .toList();
    }
}
