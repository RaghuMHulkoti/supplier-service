CREATE TABLE suppliers (
    id RAW(16) PRIMARY KEY,

    legal_name VARCHAR2(255) NOT NULL,

    display_name VARCHAR2(160) NOT NULL,

    email VARCHAR2(255) NOT NULL,

    phone VARCHAR2(20),

    tax_id VARCHAR2(40),

    country VARCHAR2(2) NOT NULL,

    onboarding_status VARCHAR2(30) DEFAULT 'PENDING' NOT NULL
        CHECK (
            onboarding_status IN (
                'PENDING',
                'KYC_SUBMITTED',
                'APPROVED',
                'REJECTED',
                'SUSPENDED'
            )
        ),

    rating NUMBER(3,2) DEFAULT 0.00 NOT NULL
        CHECK (rating >= 0 AND rating <= 5),

    commission_pct NUMBER(5,2) DEFAULT 10.00 NOT NULL
        CHECK (commission_pct >= 0 AND commission_pct <= 100),

    version NUMBER(19) DEFAULT 0 NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CHECK (INSTR(email, '@') > 1)
);

CREATE UNIQUE INDEX uq_suppliers_email_lower
    ON suppliers (LOWER(email));

CREATE INDEX idx_suppliers_status
    ON suppliers (onboarding_status);



CREATE TABLE supplier_kyc (
    supplier_id RAW(16) PRIMARY KEY
        REFERENCES suppliers(id) ON DELETE CASCADE,

    document_kind VARCHAR2(40) NOT NULL,

    document_url VARCHAR2(1000) NOT NULL,

    verified_at TIMESTAMP WITH TIME ZONE,

    verifier_ref VARCHAR2(120),

    rejection_reason VARCHAR2(4000),

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);



CREATE TABLE supplier_bank_accounts (
    id RAW(16) PRIMARY KEY,

    supplier_id RAW(16) NOT NULL
        REFERENCES suppliers(id) ON DELETE CASCADE,

    account_holder VARCHAR2(160) NOT NULL,

    account_number_enc RAW(2000) NOT NULL,

    routing_code VARCHAR2(40),

    bank_name VARCHAR2(120),

    is_primary BOOLEAN DEFAULT TRUE NOT NULL,

    verified_at TIMESTAMP WITH TIME ZONE,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_supplier_bank_supplier
    ON supplier_bank_accounts (supplier_id);



CREATE TABLE supplier_warehouses (
    id RAW(16) PRIMARY KEY,

    supplier_id RAW(16) NOT NULL
        REFERENCES suppliers(id) ON DELETE CASCADE,

    name VARCHAR2(120) NOT NULL,

    code VARCHAR2(40) NOT NULL,

    address_line1 VARCHAR2(200) NOT NULL,

    address_line2 VARCHAR2(200),

    city VARCHAR2(100) NOT NULL,

    state VARCHAR2(100) NOT NULL,

    postal_code VARCHAR2(20) NOT NULL,

    country VARCHAR2(2) NOT NULL,

    geo_lat NUMBER(9,6)
        CHECK (geo_lat BETWEEN -90 AND 90),

    geo_lng NUMBER(9,6)
        CHECK (geo_lng BETWEEN -180 AND 180),

    is_active BOOLEAN DEFAULT TRUE NOT NULL,

    version NUMBER(19) DEFAULT 0 NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT uq_supplier_warehouse_code
        UNIQUE (supplier_id, code)
);



CREATE TABLE supplier_listings (
    id RAW(16) PRIMARY KEY,

    supplier_id RAW(16) NOT NULL
        REFERENCES suppliers(id),

    product_id RAW(16) NOT NULL,

    supplier_sku VARCHAR2(80) NOT NULL,

    price NUMBER(19,4) NOT NULL
        CHECK (price > 0),

    currency VARCHAR2(3) DEFAULT 'INR' NOT NULL,

    condition VARCHAR2(20) DEFAULT 'NEW' NOT NULL
        CHECK (
            condition IN (
                'NEW',
                'REFURBISHED',
                'USED_LIKE_NEW',
                'USED_GOOD'
            )
        ),

    handling_days NUMBER(5) DEFAULT 1 NOT NULL
        CHECK (handling_days >= 0),

    is_active BOOLEAN DEFAULT TRUE NOT NULL,

    listed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    version NUMBER(19) DEFAULT 0 NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX uq_supplier_product
    ON supplier_listings (supplier_id, product_id);

CREATE UNIQUE INDEX uq_supplier_sku
    ON supplier_listings (supplier_id, supplier_sku);

CREATE INDEX idx_supplier_product_product
    ON supplier_listings (product_id, is_active);

CREATE INDEX idx_supplier_product_supplier
    ON supplier_listings (supplier_id);

CREATE INDEX idx_supplier_product_price
    ON supplier_listings (product_id, is_active, price);



CREATE TABLE supplier_payout_schedules (
    supplier_id RAW(16) PRIMARY KEY
        REFERENCES suppliers(id) ON DELETE CASCADE,

    frequency VARCHAR2(20) DEFAULT 'WEEKLY' NOT NULL
        CHECK (
            frequency IN (
                'DAILY',
                'WEEKLY',
                'MONTHLY'
            )
        ),

    next_payout_at TIMESTAMP WITH TIME ZONE,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);



CREATE TABLE supplier_outbox (
    id NUMBER(19) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,

    aggregate_id RAW(16) NOT NULL,

    event_type VARCHAR2(60) NOT NULL,

    payload VARCHAR2(32767) NOT NULL,

    status VARCHAR2(20) DEFAULT 'PENDING' NOT NULL
        CHECK (
            status IN (
                'PENDING',
                'SENT',
                'FAILED'
            )
        ),

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    sent_at TIMESTAMP WITH TIME ZONE
);

CREATE INDEX idx_supplier_outbox_pending
    ON supplier_outbox (status, created_at);
