create extension if not exists "uuid-ossp";

CREATE TABLE account (
    id UUID PRIMARY KEY default uuid_generate_v4(),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);
