create table budget_distribution_rule (
    id uuid primary key default uuid_generate_v4(),
    create_date timestamp with time zone not null,
    update_date timestamp with time zone not null,
    account_id uuid not null references account(id),
    currentExpenses integer,
    budget integer not null,
    category varchar(255) not null
);
