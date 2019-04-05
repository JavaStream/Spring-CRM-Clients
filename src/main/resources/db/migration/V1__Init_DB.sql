create table client (
    id integer not null,
    description varchar(2048),
    filename varchar(255),
    name varchar(255),
    user_id bigint,
    primary key (id)
);

create table hibernate_sequence (next_val bigint);

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

create table user_role (
    user_id bigint not null,
    roles varchar(255)
);

create table usr (
    id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table client
    add constraint client_user_fk
    foreign key (user_id) references usr (id);

alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr (id);