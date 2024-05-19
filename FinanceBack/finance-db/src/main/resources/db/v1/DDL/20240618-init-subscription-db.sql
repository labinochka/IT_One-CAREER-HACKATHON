create table subscription
(
    id                 uuid primary key default uuid_generate_v4(),
    start_date         timestamp with time zone not null,
    end_date           timestamp with time zone not null,
    account_id         uuid                     not null references account (id),
    name               varchar(255)             not null,
    description        varchar(255)             not null,
    price              decimal                  not null
);
