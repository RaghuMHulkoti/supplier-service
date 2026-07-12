package com.learning.supplier_service.gateway.mysql.kyc.entity;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "supplier_kyc")
public class SupplierKycEntity {
    @Id
    @Column(name = "supplier_id")
    private UUID supplierId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @Column(name = "document_kind", nullable = false)
    private String documentKind;

    @Column(name = "document_url", nullable = false)
    private String documentUrl;

    @Column(name = "verified_at")
    private OffsetDateTime verifiedAt;

    @Column(name = "verifier_ref")
    private String verifierRef;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected SupplierKycEntity() {
    }

    public SupplierKycEntity(SupplierEntity supplier, String documentKind, String documentUrl) {
        this.supplier = supplier;
        this.documentKind = documentKind;
        this.documentUrl = documentUrl;
    }

    @PrePersist
    void onCreate() {
        var now = OffsetDateTime.now(ZoneOffset.UTC);
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public void replaceDocument(String documentKind, String documentUrl) {
        this.documentKind = documentKind;
        this.documentUrl = documentUrl;
        this.verifiedAt = null;
        this.verifierRef = null;
        this.rejectionReason = null;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public String getDocumentKind() {
        return documentKind;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public OffsetDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public String getVerifierRef() {
        return verifierRef;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
