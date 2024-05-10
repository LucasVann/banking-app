create table accounts
(
    id                  bigint auto_increment
        primary key,
    account_holder_name varchar(255) not null,
    balance             bigint       not null
);