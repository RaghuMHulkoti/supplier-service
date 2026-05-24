package com.learning.supplier_service.core.processor.offer.create;

import com.learning.supplier_service.core.domain.OfferCondition;
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
public class CreateOfferProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private UUID productId;
    private String supplierSku;
    private BigDecimal price;
    private String currency;
    private OfferCondition condition;
    private Short handlingDays;
}
