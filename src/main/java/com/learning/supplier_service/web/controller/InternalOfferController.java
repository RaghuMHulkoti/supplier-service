package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.processor.offer.create.CreateOfferProcessor;
import com.learning.supplier_service.core.processor.offer.create.CreateOfferProcessorRequest;
import com.learning.supplier_service.core.processor.offer.get.GetOfferProcessor;
import com.learning.supplier_service.core.processor.offer.get.GetOfferProcessorRequest;
import com.learning.supplier_service.core.processor.offer.list.ListOffersProcessor;
import com.learning.supplier_service.core.processor.offer.list.ListOffersProcessorRequest;
import com.learning.supplier_service.core.processor.offer.listbyproduct.ListActiveOffersByProductProcessor;
import com.learning.supplier_service.core.processor.offer.listbyproduct.ListActiveOffersByProductProcessorRequest;
import com.learning.supplier_service.core.processor.offer.update.UpdateOfferProcessor;
import com.learning.supplier_service.core.processor.offer.update.UpdateOfferProcessorRequest;
import com.learning.supplier_service.web.model.CreateOfferRequest;
import com.learning.supplier_service.web.model.UpdateOfferRequest;
import com.learning.supplier_service.web.response.OfferResponse;
import com.learning.supplier_service.web.response.PageResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/internal/offers")
public class InternalOfferController {

    private final GetOfferProcessor getOfferProcessor;
    private final ListActiveOffersByProductProcessor listActiveOffersByProductProcessor;
    private final CreateOfferProcessor createOfferProcessor;
    private final UpdateOfferProcessor updateOfferProcessor;
    private final ListOffersProcessor listOffersProcessor;

    public InternalOfferController(
            GetOfferProcessor getOfferProcessor,
            ListActiveOffersByProductProcessor listActiveOffersByProductProcessor, CreateOfferProcessor createOfferProcessor, UpdateOfferProcessor updateOfferProcessor, ListOffersProcessor listOffersProcessor
    ) {
        this.getOfferProcessor = getOfferProcessor;
        this.listActiveOffersByProductProcessor = listActiveOffersByProductProcessor;
        this.createOfferProcessor = createOfferProcessor;
        this.updateOfferProcessor = updateOfferProcessor;
        this.listOffersProcessor = listOffersProcessor;
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

    @PostMapping("/{supplierId}/offers")
    public ResponseEntity<OfferResponse> createOffer(
            @PathVariable UUID supplierId, @Valid @RequestBody CreateOfferRequest request) {
        var response = createOfferProcessor.process(CreateOfferProcessorRequest.builder()
                .supplierId(supplierId).productId(request.productId()).supplierSku(request.supplierSku())
                .price(request.price()).currency(request.currency())
                .condition(request.condition()).handlingDays(request.handlingDays()).build());
        return ResponseEntity.status(HttpStatus.CREATED).body(OfferResponse.from(response.offer()));
    }

    @GetMapping("/{supplierId}/offers/{offerId}")
    public ResponseEntity<OfferResponse> getOffer(
            @PathVariable UUID supplierId, @PathVariable UUID offerId) {
        var response = getOfferProcessor.process(GetOfferProcessorRequest.bySupplierAndId(supplierId, offerId));
        return ResponseEntity.ok(OfferResponse.from(response.offer()));
    }

    @PatchMapping("/{supplierId}/offers/{offerId}")
    public ResponseEntity<OfferResponse> updateOffer(
            @PathVariable UUID supplierId, @PathVariable UUID offerId,
            @Valid @RequestBody UpdateOfferRequest request) {
        var response = updateOfferProcessor.process(UpdateOfferProcessorRequest.builder()
                .supplierId(supplierId).offerId(offerId).price(request.price())
                .handlingDays(request.handlingDays()).active(request.active()).build());
        return ResponseEntity.ok(OfferResponse.from(response.offer()));
    }

    @GetMapping("/{supplierId}/offers")
    public ResponseEntity<PageResponse<OfferResponse>> listOffers(
            @PathVariable UUID supplierId,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        var response = listOffersProcessor.process(new ListOffersProcessorRequest(supplierId, pageable));
        return ResponseEntity.ok(PageResponse.from(response.offers(), OfferResponse::from));
    }
}
