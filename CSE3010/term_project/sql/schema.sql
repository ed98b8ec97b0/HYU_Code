create table customer (
    cname	varchar(20) not null,
    cid		varchar(4) not null,
    cbirth	varchar(4),
    cphone	varchar(4),
    cclass	varchar(20),
    ctotal	int default 0,
    primary key (cname, cid)
);

create table staff (
    sname   varchar(20) not null,
    sid		varchar(4) not null,
    sclass	varchar(20),
    stotal	int default 0,
    primary key (sname, sid)
);

create table menu (
    mname	varchar(20) not null,
    mid		varchar(4) not null,
    mprice	int,
    primary key (mname, mid)
);

create table ordering (
    tid		int,
    cname	varchar(20),
    mname	varchar(20) not null,
    mprice	int,
    status	varchar(8) not null,
    primary key (tid)
);

create table sales (
    due	    date,
    top		varchar(20),
    bot		varchar(20),
    sale	int,
    primary key (due)
);

create table counter (
    due     date,
    mname	varchar(20) not null,
    count	int,
    primary key (due)
);
