create table administrator
(
    admin_id       int auto_increment
        primary key,
    admin_name     varchar(255) not null,
    admin_password varchar(255) null,
    constraint UK_7j00tfyenw257u66her8goa6x
        unique (admin_name)
);

create table clients
(
    id_client  int auto_increment
        primary key,
    dateOfJoin date         not null,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null,
    username   varchar(255) not null,
    constraint UK_2kc6tkdn2q51asst92r8ayiv5
        unique (dateOfJoin),
    constraint UK_srdlopksdpbh4qo20au1v8w7r
        unique (username),
    constraint UK_srv16ica2c1csub334bxjjb59
        unique (email)
);

create table accounts
(
    id_account       int    not null
        primary key,
    account_number   int    not null,
    current_balance  double null,
    client_id_client int    null,
    constraint UK_6kplolsdtr3slnvx97xsy2kc8
        unique (account_number),
    constraint FKtg0pu0bgckgx38u40l3sjo5dc
        foreign key (client_id_client) references clients (id_client)
);

create table hibernate_sequence
(
    next_val bigint null
);

create table logs
(
    id_log               int         not null
        primary key,
    log_date_time        datetime(6) null,
    client_log_id_client int         null,
    constraint FKf3ssgrufv3h5sbn5puh4ol6j6
        foreign key (client_log_id_client) references clients (id_client)
);

create table transaction_types
(
    id_transactionType int          not null
        primary key,
    transaction_type   varchar(255) null
);

create table transactions
(
    id_transaction                     int         not null
        primary key,
    to_account_number                  int         null,
    transaction_amount                 double      null,
    transaction_date_time              datetime(6) null,
    account_id_account                 int         null,
    transactionType_id_transactionType int         null,
    constraint FK2dlhq5fe2puw032mmow5204cb
        foreign key (transactionType_id_transactionType) references transaction_types (id_transactionType),
    constraint FKnnh847y64qjce6crjqoibg129
        foreign key (account_id_account) references accounts (id_account)
);

insert into transaction_types(id_transactionType, transaction_type)
values (99,'Withdraw'),
       (66,'Deposit');

insert into administrator(admin_name, admin_password)
values ('asd','asd');


