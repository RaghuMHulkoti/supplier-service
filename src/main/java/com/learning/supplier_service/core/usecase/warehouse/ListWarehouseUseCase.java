package com.learning.supplier_service.core.usecase.warehouse;

import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.gateway.WarehouseGateway;
import org.springframework.stereotype.Component;

@Component
public class ListWarehouseUseCase implements Usecase<ListWarehouseUsecaseRequest, ListWarehouseUsecaseResponse> {
	private final WarehouseGateway warehouseGateway;

	public ListWarehouseUseCase(WarehouseGateway warehouseGateway) {
		this.warehouseGateway = warehouseGateway;
	}

	@Override
	public ListWarehouseUsecaseResponse execute(ListWarehouseUsecaseRequest request) {
		return new ListWarehouseUsecaseResponse(
				warehouseGateway.findAllWarehousesBySupplierIdOrderByCreatedAtAsc(request.supplierId())
		);
	}
}
