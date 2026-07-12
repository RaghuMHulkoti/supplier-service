package com.learning.supplier_service.gateway.mysql.supplierproduct.entity;

import com.learning.supplier_service.core.domain.OfferCondition;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "supplier_listings")
public class SupplierProductEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private UUID supplierId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "supplier_sku", nullable = false)
    private String supplierSku;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal price;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferCondition condition;

    @Column(name = "handling_days", nullable = false)
    private Short handlingDays;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "listed_at", nullable = false)
    private OffsetDateTime listedAt;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected SupplierProductEntity() {
    }

    public SupplierProductEntity(
            UUID id,
            SupplierEntity supplier,
            UUID productId,
            String supplierSku,
            BigDecimal price,
            String currency,
            OfferCondition condition,
            Short handlingDays
    ) {
        this.id = id;
        this.supplier = supplier;
        this.productId = productId;
        this.supplierSku = supplierSku;
        this.price = price;
        this.currency = currency;
        this.condition = condition;
        this.handlingDays = handlingDays;
        this.active = true;
        this.listedAt = OffsetDateTime.now(ZoneOffset.UTC);
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

    public boolean updatePrice(BigDecimal newPrice) {
        if (price.compareTo(newPrice) == 0) {
            return false;
        }
        price = newPrice;
        return true;
    }

    public void updateHandlingDays(Short handlingDays) {
        this.handlingDays = handlingDays;
    }

    public boolean updateActive(Boolean active) {
        if (this.active.equals(active)) {
            return false;
        }
        this.active = active;
        return true;
    }

    public UUID getId() {
        return id;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getSupplierSku() {
        return supplierSku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public OfferCondition getCondition() {
        return condition;
    }

    public Short getHandlingDays() {
        return handlingDays;
    }

    public Boolean isActive() {
        return active;
    }

    public OffsetDateTime getListedAt() {
        return listedAt;
    }
}
