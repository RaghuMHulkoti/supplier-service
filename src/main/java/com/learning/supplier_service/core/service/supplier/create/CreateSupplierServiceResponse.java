package com.learning.supplier_service.core.service.supplier.create;

import com.learning.supplier_service.core.service.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierServiceResponse implements ServiceResponse {

    private UUID id;
    private boolean success;
}