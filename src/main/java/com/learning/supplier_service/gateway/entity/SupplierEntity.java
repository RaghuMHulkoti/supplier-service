package com.learning.supplier_service.gateway.entity;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class SupplierEntity {
    @Id
    private UUID id;

    @Column(name = "legal_name", nullable = false)
    private String legalName;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(name = "tax_id")
    private String taxId;

    @Column(nullable = false, length = 2)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "onboarding_status", nullable = false)
    private OnboardingStatus onboardingStatus;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "commission_pct", nullable = false, precision = 5, scale = 2)
    private BigDecimal commissionPct;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected SupplierEntity() {
    }

    public SupplierEntity(
            UUID id,
            String legalName,
            String displayName,
            String email,
            String phone,
            String taxId,
            String country,
            OnboardingStatus onboardingStatus,
            BigDecimal rating,
            BigDecimal commissionPct
    ) {
        this.id = id;
        this.legalName = legalName;
        this.displayName = displayName;
        this.email = email;
        this.phone = phone;
        this.taxId = taxId;
        this.country = country;
        this.onboardingStatus = onboardingStatus;
        this.rating = rating;
        this.commissionPct = commissionPct;
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

    public void markKycSubmitted() {
        onboardingStatus = OnboardingStatus.KYC_SUBMITTED;
    }

    public UUID getId() {
        return id;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getCountry() {
        return country;
    }

    public OnboardingStatus getOnboardingStatus() {
        return onboardingStatus;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public Long getVersion() {
        return version;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
