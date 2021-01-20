create database job4jdb;
create table users(
  id serial primary key,
  name varchar(16),
  age integer,
  sex boolean
);
insert into users (name, age, sex)
values('Alex', 22, true);
update users set name = 'Tom', age = 33;
delete from users;