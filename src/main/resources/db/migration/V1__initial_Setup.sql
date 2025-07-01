create table Task
(
    id          bigint auto_increment
        primary key,
    task_name   VARCHAR(255) not null,
    description varchar(255) null,
    status      varchar(255) not null
);
