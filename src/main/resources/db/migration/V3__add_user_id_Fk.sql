alter table task
    add user_id bigint not null;

alter table task
    add constraint task_user_id_fk
        foreign key (user_id) references user (id);
