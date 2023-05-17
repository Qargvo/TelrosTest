CREATE TABLE IF NOT EXISTS users
(
    id        bigint generated always as identity,
    lastname  varchar(30),
    firstname varchar(40),
    surname   varchar(40),
    birthday  varchar(40),
    telephone varchar(40),
    email     varchar(40),
    primary key (id)
);