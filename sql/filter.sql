
create table type(
  id serial primary key,
  name varchar(30)
);
create table product(
  id serial primary key,
  name varchar(30),
  type_id int references type(id),
  expired_date date,
  price int
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженное');

insert into product(name, type_id, expired_date, price) values ('Сыр1', 1, '1999-01-08', 100);
insert into product(name, type_id, expired_date, price) values ('Сыр2', 1, '2000-01-08', 150);
insert into product(name, type_id, expired_date, price) values ('Сыр3', 1, '2010-01-08', 300);

insert into product(name, type_id, expired_date, price) values ('Молоко1', 2, '2010-03-08', 200);
insert into product(name, type_id, expired_date, price) values ('Молоко2', 2, '2013-05-08', 44);
insert into product(name, type_id, expired_date, price) values ('Молоко3', 2, '2014-02-08', 42);

insert into product(name, type_id, expired_date, price) values ('Мороженное1', 3, '2008-01-11', 24);
insert into product(name, type_id, expired_date, price) values ('Мороженное1', 3, '2005-02-23', 53);
insert into product(name, type_id, expired_date, price) values ('Мороженное1', 3, '2006-05-22', 555);

select * from product join type t on product.type_id = t.id where t.name = 'Сыр';
select * from product join type t on product.type_id = t.id where product.name like '%Мороженное%';
select * from product where product.expired_date =  now() + interval '1 month';
select * from product where price = (select max(price) from product);
select count(name) from type;
select type.name from type where (select count(p.id) from product as p where p.type_id = type.id) < 10;
select * from product join type t on product.type_id = t.id;