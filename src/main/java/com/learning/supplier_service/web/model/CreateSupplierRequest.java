package com.learning.supplier_service.web.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateSupplierRequest(
        @NotBlank(message = "Legal name is required")
        @Size(max = 255, message = "Legal name must be at most 255 characters")
        String legalName,

        @NotBlank(message = "Display name is required")
        @Size(max = 160, message = "Display name must be at most 160 characters")
        String displayName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 255, message = "Email must be at most 255 characters")
        String email,

        @Size(max = 20, message = "Phone must be at most 20 characters")
        String phone,

        @Size(max = 40, message = "Tax ID must be at most 40 characters")
        String taxId,

        @NotBlank(message = "Country is required")
        @Pattern(regexp = "[A-Za-z]{2}", message = "Country must be a 2-letter code")
        String country,

        @DecimalMin(value = "0.00", inclusive = true, message = "Commission percentage cannot be negative")
        BigDecimal commissionPct
) {
}
