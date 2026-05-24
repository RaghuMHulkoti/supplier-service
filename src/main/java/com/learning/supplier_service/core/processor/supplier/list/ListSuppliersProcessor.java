package com.learning.supplier_service.core.processor.supplier.list;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.list.ListSuppliersUseCase;
import com.learning.supplier_service.core.usecase.supplier.list.ListSuppliersUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class ListSuppliersProcessor implements Processor<ListSuppliersProcessorRequest, ListSuppliersProcessorResponse> {

    private final ListSuppliersUseCase listSuppliersUseCase;

    public ListSuppliersProcessor(ListSuppliersUseCase listSuppliersUseCase) {
        this.listSuppliersUseCase = listSuppliersUseCase;
    }

    @Override
    public ListSuppliersProcessorResponse process(ListSuppliersProcessorRequest request) {
        var response = listSuppliersUseCase.execute(new ListSuppliersUsecaseRequest(request.pageable()));
        return new ListSuppliersProcessorResponse(response.suppliers());
    }
}
