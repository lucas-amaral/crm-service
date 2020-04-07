CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    name TEXT,
    state VARCHAR(2)
);

CREATE TABLE neighborhoods (
    id SERIAL PRIMARY KEY,
    name TEXT,
    city_id INTEGER REFERENCES cities(id)
);

CREATE TABLE streets (
    zip_code VARCHAR(9) PRIMARY KEY,
    name TEXT,
    neighborhood_id INTEGER REFERENCES neighborhoods(id)
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street_zip_code VARCHAR(9) REFERENCES streets(zip_code),
    number VARCHAR(10) NOT NULL,
    complement TEXT
);

CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    date_of_birth DATE,
    type VARCHAR(20),
    cpf_cnpj TEXT,
    sex VARCHAR(20),
    address_id INTEGER REFERENCES addresses(id),
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'UTC')
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE TABLE properties (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) REFERENCES users(username) ON DELETE CASCADE,
    description TEXT NOT NULL,
    type VARCHAR(20),
    area NUMERIC(19, 2),
    registration INTEGER,
    address_id INTEGER REFERENCES addresses(id),
    iptu INTEGER,
    dorms INTEGER,
    suites INTEGER,
    bathrooms INTEGER,
    pool BOOLEAN,
    balcony BOOLEAN,
    elevator BOOLEAN,
    barbecue_grill BOOLEAN,
    images TEXT ARRAY,
    enable BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'UTC')
);

CREATE TABLE garages (
    id SERIAL PRIMARY KEY,
    property_id INTEGER REFERENCES properties(id) ON UPDATE CASCADE ON DELETE CASCADE,
    box INTEGER,
    registration TEXT
);

CREATE TABLE sales (
    id SERIAL PRIMARY KEY,
    property_id INTEGER REFERENCES properties(id) ON UPDATE CASCADE ON DELETE CASCADE,
    value NUMERIC(19, 2),
    financing BOOLEAN,
    financing_value NUMERIC(19, 2),
    barter_vehicle BOOLEAN,
    barter_vehicle_value NUMERIC(19, 2),
    barter_property BOOLEAN,
    barter_property_value NUMERIC(19, 2),
    agencying DATE
);

CREATE TABLE interests (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE,
    value NUMERIC(19, 2),
    financing BOOLEAN,
    financing_value NUMERIC(19, 2),
    types VARCHAR(20) ARRAY,
    dorms INTEGER,
    suites INTEGER,
    bathrooms INTEGER,
    pool BOOLEAN,
    balcony BOOLEAN,
    elevator BOOLEAN,
    barbecue_grill BOOLEAN,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'UTC')
);

CREATE TABLE interest_neighborhoods (
    interest_id INTEGER REFERENCES interests(id),
    neighborhood_id INTEGER REFERENCES neighborhoods(id),
    PRIMARY KEY (interest_id, neighborhood_id)
);

CREATE TABLE barters (
    id SERIAL PRIMARY KEY,
    interest_id INTEGER REFERENCES interests(id) ON UPDATE CASCADE ON DELETE CASCADE,
    type VARCHAR(30) NOT NULL,
    value NUMERIC(19, 2),
    images TEXT ARRAY
);