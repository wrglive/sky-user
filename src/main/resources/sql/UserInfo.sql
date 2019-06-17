create table user_info
(
    user_id   bigint unsigned               not null
        primary key,
    nick_name varchar(50)                   not null,
    gender    tinyint(2) unsigned           not null,
    avatar_id bigint                        null,
    status    tinyint(1) unsigned default 0 not null
)
    comment '用户表';

