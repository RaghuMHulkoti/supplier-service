package com.learning.supplier_service.gateway.mysql.wareHouse.entity;

import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import jakarta.persistence.*;

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

    private SupplierWarehouseEntity(Builder builder) {
        this.id = builder.id;
        this.supplier = builder.supplier;
        this.name = builder.name;
        this.code = builder.code;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.geoLat = builder.geoLat;
        this.geoLng = builder.geoLng;
        this.active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID id;
        private SupplierEntity supplier;
        private String name;
        private String code;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private BigDecimal geoLat;
        private BigDecimal geoLng;
        private Boolean active = true;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder supplier(SupplierEntity supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder addressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder addressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder geoLat(BigDecimal geoLat) {
            this.geoLat = geoLat;
            return this;
        }

        public Builder geoLng(BigDecimal geoLng) {
            this.geoLng = geoLng;
            return this;
        }

        public Builder active(Boolean active) {
            this.active = active;
            return this;
        }

        public SupplierWarehouseEntity build() {
            return new SupplierWarehouseEntity(this);
        }
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

    public void update(String name, String addressLine1, String addressLine2,
                       String city, String state, String postalCode, String country,
                       BigDecimal geoLat, BigDecimal geoLng) {
        if (name != null) this.name = name;
        if (addressLine1 != null) this.addressLine1 = addressLine1;
        if (addressLine2 != null) this.addressLine2 = addressLine2;
        if (city != null) this.city = city;
        if (state != null) this.state = state;
        if (postalCode != null) this.postalCode = postalCode;
        if (country != null) this.country = country;
        if (geoLat != null) this.geoLat = geoLat;
        if (geoLng != null) this.geoLng = geoLng;
    }

    public void setActive(boolean active) {
        this.active = active;
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