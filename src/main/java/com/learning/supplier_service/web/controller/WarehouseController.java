package com.learning.supplier_service.web.controller;

import com.learning.supplier_service.core.processor.warehouse.create.CreateWareHouseProcessor;
import com.learning.supplier_service.core.processor.warehouse.create.CreateWareHouseProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.get.GetWarehouseProcessor;
import com.learning.supplier_service.core.processor.warehouse.get.GetWarehouseProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.list.ListWarehousesProcessor;
import com.learning.supplier_service.core.processor.warehouse.list.ListWarehousesProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.status.ToggleWarehouseStatusProcessor;
import com.learning.supplier_service.core.processor.warehouse.status.ToggleWarehouseStatusProcessorRequest;
import com.learning.supplier_service.core.processor.warehouse.update.UpdateWarehouseProcessor;
import com.learning.supplier_service.core.processor.warehouse.update.UpdateWarehouseProcessorRequest;
import com.learning.supplier_service.web.model.CreateWarehouseRequest;
import com.learning.supplier_service.web.model.ToggleWarehouseStatusRequest;
import com.learning.supplier_service.web.model.UpdateWarehouseRequest;
import com.learning.supplier_service.web.response.WarehouseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    private final CreateWareHouseProcessor createWareHouseProcessor;
    private final ListWarehousesProcessor listWarehousesProcessor;
    private final GetWarehouseProcessor getWarehouseProcessor;
    private final UpdateWarehouseProcessor updateWarehouseProcessor;
    private final ToggleWarehouseStatusProcessor toggleWarehouseStatusProcessor;

    public WarehouseController(CreateWareHouseProcessor createWareHouseProcessor, ListWarehousesProcessor listWarehousesProcessor, GetWarehouseProcessor getWarehouseProcessor, UpdateWarehouseProcessor updateWarehouseProcessor, ToggleWarehouseStatusProcessor toggleWarehouseStatusProcessor) {
        this.createWareHouseProcessor = createWareHouseProcessor;
        this.listWarehousesProcessor = listWarehousesProcessor;
        this.getWarehouseProcessor = getWarehouseProcessor;
        this.updateWarehouseProcessor = updateWarehouseProcessor;
        this.toggleWarehouseStatusProcessor = toggleWarehouseStatusProcessor;
    }

    @PostMapping("/{supplierId}/warehouses")
    public ResponseEntity<Object> createWarehouse(
            @Valid @RequestBody CreateWarehouseRequest request) {
        var response = createWareHouseProcessor.process(buildCreateWarehouseRequest(request.supplierId(), request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{supplierId}/warehouses")
    public ResponseEntity<List<WarehouseResponse>> listWarehouses(@PathVariable UUID supplierId) {
        var response = listWarehousesProcessor.process(new ListWarehousesProcessorRequest(supplierId));
        return ResponseEntity.ok(response.warehouses().stream().map(WarehouseResponse::from).toList());
    }

    @GetMapping("/{supplierId}/warehouses/{warehouseId}")
    public ResponseEntity<WarehouseResponse> getWarehouse(
            @PathVariable UUID supplierId, @PathVariable UUID warehouseId) {
        var response = getWarehouseProcessor.process(new GetWarehouseProcessorRequest(supplierId, warehouseId));
        return ResponseEntity.ok(WarehouseResponse.from(response.warehouse()));
    }

    @PatchMapping("/{supplierId}/warehouses/{warehouseId}")
    public ResponseEntity<WarehouseResponse> updateWarehouse(
            @PathVariable UUID supplierId, @PathVariable UUID warehouseId,
            @Valid @RequestBody UpdateWarehouseRequest request) {
        var response = updateWarehouseProcessor.process(UpdateWarehouseProcessorRequest.builder()
                .supplierId(supplierId).warehouseId(warehouseId).name(request.name())
                .addressLine1(request.addressLine1()).addressLine2(request.addressLine2())
                .city(request.city()).state(request.state()).postalCode(request.postalCode())
                .country(request.country()).geoLat(request.geoLat()).geoLng(request.geoLng()).build());
        return ResponseEntity.ok(WarehouseResponse.from(response.warehouse()));
    }

    @PatchMapping("/{supplierId}/warehouses/{warehouseId}/status")
    public ResponseEntity<Object> toggleWarehouseStatus(
            @PathVariable UUID supplierId, @PathVariable UUID warehouseId,
            @Valid @RequestBody ToggleWarehouseStatusRequest request) {
        var response = toggleWarehouseStatusProcessor.process(
                ToggleWarehouseStatusProcessorRequest.builder()
                        .supplierId(supplierId).warehouseId(warehouseId).active(request.active()).build());
        return ResponseEntity.ok(response);
    }

    private static CreateWareHouseProcessorRequest buildCreateWarehouseRequest(UUID supplierId, CreateWarehouseRequest request) {
        return CreateWareHouseProcessorRequest.builder()
                .supplierId(supplierId).name(request.name()).code(request.code())
                .addressLine1(request.addressLine1()).addressLine2(request.addressLine2())
                .city(request.city()).state(request.state()).postalCode(request.postalCode())
                .country(request.country()).geoLat(request.geoLat()).geoLng(request.geoLng()).build();
    }
}
