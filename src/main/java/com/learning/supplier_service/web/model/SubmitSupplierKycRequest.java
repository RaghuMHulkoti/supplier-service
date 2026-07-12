package com.learning.supplier_service.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SubmitSupplierKycRequest(
        @NotBlank(message = "Document kind is required")
        @Size(max = 40, message = "Document kind must be at most 40 characters")
        String documentKind,

        @NotBlank(message = "Document URL is required")
        @Size(max = 1000, message = "Document URL must be at most 1000 characters")
        String documentUrl
) {
}
