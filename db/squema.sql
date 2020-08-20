create table users (
    id       serial primary key,
    name     text,
    email    text,
    password text,
    unique (email)
)

create table cars (
    id serial primary key,
    name text,
    photo text,
    user_id int,
    color text,
    type text,
    body_car text,
    engine text,
    mileage text,
    created text,
    price text,
    publish timestamp,
    status boolean
)