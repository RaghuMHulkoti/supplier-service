package com.learning.supplier_service.web.model;

import jakarta.validation.constraints.NotNull;

public record ToggleWarehouseStatusRequest(
        @NotNull(message = "Active flag is required")
        Boolean active
) {
}
