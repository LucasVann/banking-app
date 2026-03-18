create database banking_app;

create table accounts
(
    id bigint auto_increment,
    account_holder_name varchar(255) not null,
    balance             bigint       not null,
	primary key (id)
);