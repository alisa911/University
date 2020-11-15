drop table if exists departments cascade;
drop table if exists lectors cascade;
drop table if exists departments_lectors cascade;
drop table if exists degrees cascade;
drop sequence if exists global_seq cascade;

create sequence global_seq as integer start with 1;

create table "departments"
(
    "id"                  bigserial               not null,
    "name"                varchar(255)            not null unique,
    "head_id"             bigserial               not null,
    constraint "departments_pk" primary key ("id")
);

create table "lectors"
(
    "id"                  bigserial               not null,
    "name"                varchar(255)            not null,
    "degree_id"           bigserial               not null,
    "salary"              bigint,
    constraint "lectors_pk" primary key ("id")
);

create table "departments_lectors"
(
    "department_id"        bigserial               not null,
    "lector_id"            bigserial               not null,
    constraint "role_permission_pk" primary key ("department_id", "lector_id")
);

create table "degrees"
(
    "id"                  bigserial               not null,
    "name"                varchar(255)            not null unique,
    constraint "degree_pk" primary key ("id")

);

alter table "departments"
    add constraint "departments_fk1" foreign key ("head_id") references "lectors" ("id") on delete cascade;
alter table "departments_lectors"
    add constraint "departments_fk1" foreign key ("department_id") references "departments" ("id") on delete cascade;
alter table "departments_lectors"
    add constraint "departments_fk2" foreign key ("lector_id") references "lectors" ("id") on delete cascade;
alter table "lectors"
    add constraint "lectors_fk1" foreign key ("degree_id") references "degrees" ("id") on delete cascade;
