create TABLE member (
    id          bigint      auto_increment      primary key,
    user_id     varchar(20) unique not null,
    password    varchar(20) not null,
    name        varchar(20) not null,
    phone       varchar(11) not null,
    member_type int         not null,
    create_at   timestamp   default     current_timestamp,
    update _at  timestamp   default on update current_timestamp
);