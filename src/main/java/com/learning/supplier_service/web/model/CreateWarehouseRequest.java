package com.learning.supplier_service.web.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateWarehouseRequest(
        @NotBlank(message = "Warehouse name is required")
        @Size(max = 120, message = "Warehouse name must be at most 120 characters")
        String name,

        @NotBlank(message = "Warehouse code is required")
        @Size(max = 40, message = "Warehouse code must be at most 40 characters")
        String code,

        @NotBlank(message = "Address line 1 is required")
        @Size(max = 200, message = "Address line 1 must be at most 200 characters")
        String addressLine1,

        @Size(max = 200, message = "Address line 2 must be at most 200 characters")
        String addressLine2,

        @NotBlank(message = "City is required")
        @Size(max = 100, message = "City must be at most 100 characters")
        String city,

        @NotBlank(message = "State is required")
        @Size(max = 100, message = "State must be at most 100 characters")
        String state,

        @NotBlank(message = "Postal code is required")
        @Size(max = 20, message = "Postal code must be at most 20 characters")
        String postalCode,

        @NotBlank(message = "Country is required")
        @Pattern(regexp = "[A-Za-z]{2}", message = "Country must be a 2-letter code")
        String country,

        @DecimalMin(value = "-90.0", message = "Latitude must be at least -90")
        @DecimalMax(value = "90.0", message = "Latitude must be at most 90")
        BigDecimal geoLat,

        @DecimalMin(value = "-180.0", message = "Longitude must be at least -180")
        @DecimalMax(value = "180.0", message = "Longitude must be at most 180")
        BigDecimal geoLng
) {
}
