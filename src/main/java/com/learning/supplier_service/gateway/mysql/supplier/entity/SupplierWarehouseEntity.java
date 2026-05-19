package com.learning.supplier_service.gateway.mysql.supplier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "supplier_warehouses")
public class SupplierWarehouseEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private UUID supplierId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(nullable = false, length = 2)
    private String country;

    @Column(name = "geo_lat", precision = 9, scale = 6)
    private BigDecimal geoLat;

    @Column(name = "geo_lng", precision = 9, scale = 6)
    private BigDecimal geoLng;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected SupplierWarehouseEntity() {
    }

    public SupplierWarehouseEntity(
            UUID id,
            SupplierEntity supplier,
            String name,
            String code,
            String addressLine1,
            String addressLine2,
            String city,
            String state,
            String postalCode,
            String country,
            BigDecimal geoLat,
            BigDecimal geoLng
    ) {
        this.id = id;
        this.supplier = supplier;
        this.name = name;
        this.code = code;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
        this.active = true;
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

    public UUID getId() {
        return id;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public BigDecimal getGeoLat() {
        return geoLat;
    }

    public BigDecimal getGeoLng() {
        return geoLng;
    }

    public Boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
