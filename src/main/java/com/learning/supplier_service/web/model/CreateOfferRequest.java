package com.learning.supplier_service.web.model;

import com.learning.supplier_service.core.domain.OfferCondition;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOfferRequest(
        @NotNull(message = "Product ID is required")
        UUID productId,

        @NotBlank(message = "Supplier SKU is required")
        @Size(max = 80, message = "Supplier SKU must be at most 80 characters")
        String supplierSku,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0001", message = "Price must be positive")
        BigDecimal price,

        @NotBlank(message = "Currency is required")
        @Pattern(regexp = "[A-Za-z]{3}", message = "Currency must be a 3-letter code")
        String currency,

        @NotNull(message = "Condition is required")
        OfferCondition condition,

        @NotNull(message = "Handling days is required")
        @Min(value = 0, message = "Handling days cannot be negative")
        Short handlingDays
) {
}
