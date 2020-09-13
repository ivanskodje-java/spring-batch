drop table if exists company CASCADE;
drop table if exists employee CASCADE;
drop table if exists employee_hash CASCADE;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 1 increment by 1;

create table company
(
    id          bigint       not null,
    description varchar(255),
    name        varchar(255) not null,
    primary key (id)
);

alter table company
    add constraint UK_COMPANY_NAME unique (name);

create table employee
(
    id            bigint       not null,
    description   varchar(255),
    leave         int default 0,
    name          varchar(255) not null,
    serial_number varchar(255) not null,
    company_id    bigint       not null,
    primary key (id)
);

alter table employee
    add constraint FK_EMPLOYEE_COMPANY foreign key (company_id) references company;

create table employee_hash
(
    id          bigint  not null,
    hash        integer not null,
    employee_id bigint  not null,
    primary key (id)
);

alter table employee_hash
    add constraint UK_EMPLOYEE_ID unique (employee_id);

alter table employee_hash
    add constraint FK_EMPLOYEEHASH_EMPLOYEE foreign key (employee_id) references employee on delete cascade