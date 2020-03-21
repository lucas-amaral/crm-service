CREATE TABLE states (
    id VARCHAR(2) PRIMARY KEY,
    name TEXT
);

CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    name TEXT,
    state_id VARCHAR(2) FOREIGN KEY REFERENCES states(id)
);

CREATE TABLE neighborhoods (
    id SERIAL PRIMARY KEY,
    name TEXT,
    city_id LONG FOREIGN KEY REFERENCES cities(id),
    zip_code TEXT
);

CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    street TEXT NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement TEXT,
    neighborhood_id LONG FOREIGN KEY REFERENCES cities(id)
);

CREATE TABLE user(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    date_of_birth DATE,
    email VARCHAR(40) NOT NULL,
    type VARCHAR(20),
    cpf_cnpj TEXT,
    sex VARCHAR(20),
    address_id LONG FOREIGN KEY REFERENCES address(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'UTC')
);

CREATE TABLE properties(
    id SERIAL PRIMARY KEY,
    user_id LONG FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE,
    description TEXT NOT NULL,
    type VARCHAR(20),
    area DOUBLE,
    registration LONG,
    address_id LONG FOREIGN KEY REFERENCES address(id),
    iptu LONG,
    dorms INTEGER,
    suites INTEGER,
    bathrooms INTEGER,
    pool BOOLEAN,
    balcony BOLLEAN,
    elevator BOLLEAN,
    barbecue_grill BOLLEAN,
    images TEXT ARRAY,
    enable BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'UTC')
);

CREATE TABLE garages(
    id SERIAL PRIMARY KEY,
    property_id LONG FOREIGN KEY REFERENCES properties(id) ON DELETE CASCADE,
    box INTEGER,
    registration TEXT
);

CREATE TABLE sales(
    id SERIAL PRIMARY KEY,
    property_id LONG FOREIGN KEY REFERENCES properties(id) ON DELETE CASCADE,
    value DOUBLE,
    financing BOOLEAN,
    financing_value DOUBLE,
    barter_vehicle BOOLEAN,
    barter_vehicle_value DOUBLE,
    barter_property BOOLEAN,
    barter_property_value DOUBLE,
    agencying DATE
);

CREATE TABLE interests(
    id SERIAL PRIMARY KEY,
    user_id LONG FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE,
    value DOUBLE,
    financing BOOLEAN,
    financing_value DOUBLE,
    types VARCHAR(20) ARRAY,
    neighborhood_ids LONG ARRAY,
    dorms INTEGER,
    suites INTEGER,
    bathrooms INTEGER,
    pool BOOLEAN,
    balcony BOLLEAN,
    elevator BOLLEAN,
    barbecue_grill BOLLEAN,
    agencying DATE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'UTC')
);

CREATE TABLE barters(
    id SERIAL PRIMARY KEY,
    interest_id LONG FOREIGN KEY REFERENCES interests(id) ON DELETE CASCADE,
    type VARCHAR(30) NOT NULL,
    value DOUBLE,
    images TEXT ARRAY
);