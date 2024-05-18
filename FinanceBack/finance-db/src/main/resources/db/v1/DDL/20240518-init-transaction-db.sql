create table transaction (
    id uuid primary key default uuid_generate_v4(),
    create_date timestamp with time zone not null,
    update_date timestamp with time zone not null,
    account_id uuid not null references account(id),
    date timestamp not null,
    amount integer not null,
    category varchar(255) not null,
    transactional_type varchar(255) not null,
    is_editable boolean not null
);
