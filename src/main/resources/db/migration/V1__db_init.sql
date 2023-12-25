create table account (
        id integer not null auto_increment,
        user_email varchar(255),
        user_first_name varchar(255),
        user_last_name varchar(255),
        user_password varchar(255),
        primary key (id)
    ) engine=InnoDB;

create table account_roles (
        account_id integer not null,
        role_id integer not null
    ) engine=InnoDB;

create table role (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;
