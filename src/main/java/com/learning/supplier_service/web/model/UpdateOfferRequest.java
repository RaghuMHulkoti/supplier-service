package com.learning.supplier_service.web.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record UpdateOfferRequest(
        @DecimalMin(value = "0.0001", message = "Price must be positive")
        BigDecimal price,

        @Min(value = 0, message = "Handling days cannot be negative")
        Short handlingDays,

        Boolean active
) {
    @AssertTrue(message = "At least one offer field must be provided")
    public boolean hasAtLeastOneField() {
        return price != null || handlingDays != null || active != null;
    }
}
