create table role(
    id serial primary key,
    role varchar(30)
);
create table users(
    id serial primary key,
    name varchar(16),
    age int,
    role_id int references role(id)
);
create table rules(
    id serial primary key,
    name varchar(16)
);
create table role_rules(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);
create table category(
    id serial primary key,
    category varchar(300)
);
create table state(
    id serial primary key,
    state varchar(30)
);
create table item(
    id serial primary key,
    subject varchar(30),
    description varchar(300),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);
create table comments(
    id serial primary key,
    comment varchar(300),
    item_id int references item(id)
);
create table attachments(
    id serial primary key,
    attachment bytea,
    item_id int references item(id)
);

