create table User
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(50)  not null,
    password varchar(255) not null
);

