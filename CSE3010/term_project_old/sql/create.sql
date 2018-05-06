create table Customer (
    name varchar(20) not null,
    id varchar(8) not null,
    birth varchar(8),
    phone varchar(11),
    level varchar(20) default 'Normal',
    sale int default 0,
    primary key(name, id)
);

create table Staff (
    name varchar(20) not null,
    id varchar(8) null,
    position varchar(20) not null,
    sale int default 0,
    primary key(name, id)
);

create table Menu (
    name varchar(30) not null,
    id varchar(8) not null,
    price int default 0,
    primary key(name, id)
);

create table DaySale (
    day date,
    top_menu varchar(20) not null,
    bot_menu varchar(20) not null,
    sale int default 0,
    primary keY(day)
);
