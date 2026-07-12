package com.learning.supplier_service.web.response;

import com.learning.supplier_service.gateway.mysql.kyc.entity.SupplierKycEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public record KycDetailResponse(
        UUID supplierId,
        String documentKind,
        String documentUrl,
        OffsetDateTime verifiedAt,
        String verifierRef,
        String rejectionReason
) {
    public static KycDetailResponse from(SupplierKycEntity kyc) {
        return new KycDetailResponse(
                kyc.getSupplierId(),
                kyc.getDocumentKind(),
                kyc.getDocumentUrl(),
                kyc.getVerifiedAt(),
                kyc.getVerifierRef(),
                kyc.getRejectionReason()
        );
    }
}
