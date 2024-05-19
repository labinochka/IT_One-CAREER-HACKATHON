create table transaction_in_bank
(
    id                 uuid primary key default uuid_generate_v4(),
    mail               varchar(255) not null,
    date               timestamp    not null,
    amount             integer      not null,
    category           varchar(255) not null,
    transactional_type varchar(255) not null
);
