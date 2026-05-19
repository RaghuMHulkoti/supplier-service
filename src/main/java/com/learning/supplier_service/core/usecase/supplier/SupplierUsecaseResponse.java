package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierUsecaseResponse implements UsecaseResponse {

    private UUID id;
    private boolean success;
}