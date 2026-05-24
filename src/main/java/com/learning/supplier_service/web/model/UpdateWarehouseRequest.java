package com.learning.supplier_service.web.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateWarehouseRequest(
        @Size(max = 120, message = "Warehouse name must be at most 120 characters")
        String name,

        @Size(max = 200, message = "Address line 1 must be at most 200 characters")
        String addressLine1,

        @Size(max = 200, message = "Address line 2 must be at most 200 characters")
        String addressLine2,

        @Size(max = 100, message = "City must be at most 100 characters")
        String city,

        @Size(max = 100, message = "State must be at most 100 characters")
        String state,

        @Size(max = 20, message = "Postal code must be at most 20 characters")
        String postalCode,

        @Pattern(regexp = "[A-Za-z]{2}", message = "Country must be a 2-letter code")
        String country,

        @DecimalMin(value = "-90.0", message = "Latitude must be at least -90")
        @DecimalMax(value = "90.0", message = "Latitude must be at most 90")
        BigDecimal geoLat,

        @DecimalMin(value = "-180.0", message = "Longitude must be at least -180")
        @DecimalMax(value = "180.0", message = "Longitude must be at most 180")
        BigDecimal geoLng
) {
    @AssertTrue(message = "At least one warehouse field must be provided")
    public boolean hasAtLeastOneField() {
        return name != null || addressLine1 != null || addressLine2 != null
                || city != null || state != null || postalCode != null
                || country != null || geoLat != null || geoLng != null;
    }
}
