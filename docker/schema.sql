--CREATE TYPE currency AS ENUM ('EUR', 'SEK', 'GBP', 'USD');

--CREATE TYPE transaction_direction AS ENUM ('IN', 'OUT');

CREATE TABLE ACCOUNTS (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    country VARCHAR
);

CREATE TABLE BALANCES (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT,
    currency VARCHAR,
    balance DOUBLE PRECISION
);

CREATE TABLE TRANSACTIONS (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT,
    amount DOUBLE PRECISION,
    currency VARCHAR,
    transaction_direction VARCHAR,
    description VARCHAR
)