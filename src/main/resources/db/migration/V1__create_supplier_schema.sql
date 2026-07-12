CREATE TABLE suppliers (
    id UUID PRIMARY KEY,

    legal_name VARCHAR(255) NOT NULL,

    display_name VARCHAR(160) NOT NULL,

    email VARCHAR(255) NOT NULL,

    phone VARCHAR(20),

    tax_id VARCHAR(40),

    country VARCHAR(2) NOT NULL,

    onboarding_status VARCHAR(30) NOT NULL DEFAULT 'PENDING'
        CHECK (
            onboarding_status IN (
                'PENDING',
                'KYC_SUBMITTED',
                'APPROVED',
                'REJECTED',
                'SUSPENDED'
            )
        ),

    rating NUMERIC(3,2) NOT NULL DEFAULT 0.00
        CHECK (rating >= 0 AND rating <= 5),

    commission_pct NUMERIC(5,2) NOT NULL DEFAULT 10.00
        CHECK (commission_pct >= 0 AND commission_pct <= 100),

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CHECK (POSITION('@' IN email) > 1)
);

CREATE UNIQUE INDEX uq_suppliers_email_lower
    ON suppliers (LOWER(email));

CREATE INDEX idx_suppliers_status
    ON suppliers (onboarding_status);



CREATE TABLE supplier_kyc (
    supplier_id UUID PRIMARY KEY
        REFERENCES suppliers(id) ON DELETE CASCADE,

    document_kind VARCHAR(40) NOT NULL,

    document_url VARCHAR(1000) NOT NULL,

    verified_at TIMESTAMPTZ,

    verifier_ref VARCHAR(120),

    rejection_reason TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE supplier_bank_accounts (
    id UUID PRIMARY KEY,

    supplier_id UUID NOT NULL
        REFERENCES suppliers(id) ON DELETE CASCADE,

    account_holder VARCHAR(160) NOT NULL,

    account_number_enc BYTEA NOT NULL,

    routing_code VARCHAR(40),

    bank_name VARCHAR(120),

    is_primary BOOLEAN NOT NULL DEFAULT TRUE,

    verified_at TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_supplier_bank_supplier
    ON supplier_bank_accounts (supplier_id);



CREATE TABLE supplier_warehouses (
    id UUID PRIMARY KEY,

    supplier_id UUID NOT NULL
        REFERENCES suppliers(id) ON DELETE CASCADE,

    name VARCHAR(120) NOT NULL,

    code VARCHAR(40) NOT NULL,

    address_line1 VARCHAR(200) NOT NULL,

    address_line2 VARCHAR(200),

    city VARCHAR(100) NOT NULL,

    state VARCHAR(100) NOT NULL,

    postal_code VARCHAR(20) NOT NULL,

    country VARCHAR(2) NOT NULL,

    geo_lat NUMERIC(9,6)
        CHECK (geo_lat BETWEEN -90 AND 90),

    geo_lng NUMERIC(9,6)
        CHECK (geo_lng BETWEEN -180 AND 180),

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_supplier_warehouse_code
        UNIQUE (supplier_id, code)
);



CREATE TABLE supplier_listings (
    id UUID PRIMARY KEY,

    supplier_id UUID NOT NULL
        REFERENCES suppliers(id) ON DELETE RESTRICT,

    product_id UUID NOT NULL,

    supplier_sku VARCHAR(80) NOT NULL,

    price NUMERIC(19,4) NOT NULL
        CHECK (price > 0),

    currency VARCHAR(3) NOT NULL DEFAULT 'INR',

    condition VARCHAR(20) NOT NULL DEFAULT 'NEW'
        CHECK (
            condition IN (
                'NEW',
                'REFURBISHED',
                'USED_LIKE_NEW',
                'USED_GOOD'
            )
        ),

    handling_days SMALLINT NOT NULL DEFAULT 1
        CHECK (handling_days >= 0),

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    listed_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uq_supplier_product
    ON supplier_listings (supplier_id, product_id);

CREATE UNIQUE INDEX uq_supplier_sku
    ON supplier_listings (supplier_id, supplier_sku);

CREATE INDEX idx_supplier_product_product
    ON supplier_listings (product_id)
    WHERE is_active = TRUE;

CREATE INDEX idx_supplier_product_supplier
    ON supplier_listings (supplier_id);

CREATE INDEX idx_supplier_product_price
    ON supplier_listings (product_id, price)
    WHERE is_active = TRUE;



CREATE TABLE supplier_payout_schedules (
    supplier_id UUID PRIMARY KEY
        REFERENCES suppliers(id) ON DELETE CASCADE,

    frequency VARCHAR(20) NOT NULL DEFAULT 'WEEKLY'
        CHECK (
            frequency IN (
                'DAILY',
                'WEEKLY',
                'MONTHLY'
            )
        ),

    next_payout_at TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE supplier_outbox (
    id BIGSERIAL PRIMARY KEY,

    aggregate_id UUID NOT NULL,

    event_type VARCHAR(60) NOT NULL,

    payload TEXT NOT NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING'
        CHECK (
            status IN (
                'PENDING',
                'SENT',
                'FAILED'
            )
        ),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    sent_at TIMESTAMPTZ
);

CREATE INDEX idx_supplier_outbox_pending
    ON supplier_outbox (status, created_at)
    WHERE status = 'PENDING';