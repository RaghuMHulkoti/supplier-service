package com.learning.supplier_service.core.usecase.warehouse;

import com.learning.supplier_service.core.service.warehouse.WarehouseService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class ListWarehouseUseCase implements Usecase<ListWarehouseUsecaseRequest, ListWarehouseUsecaseResponse> {
	private final WarehouseService warehouseService;

	public ListWarehouseUseCase(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@Override
	public ListWarehouseUsecaseResponse execute(ListWarehouseUsecaseRequest request) {
		return new ListWarehouseUsecaseResponse(warehouseService.list(request.supplierId()));
	}
}

