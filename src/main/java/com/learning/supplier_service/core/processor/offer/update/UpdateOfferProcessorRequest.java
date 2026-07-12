package com.learning.supplier_service.core.processor.offer.update;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOfferProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private UUID offerId;
    private BigDecimal price;
    private Short handlingDays;
    private Boolean active;
}
