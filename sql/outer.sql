--1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(30)
);
create table employees(
    id serial primary key,
    name varchar(30),
    dep_id int references departments(id)
);

insert into departments(name) values ('IT billing');
insert into departments(name) values ('Finance');
insert into departments(name) values ('Engineering');

insert into employees(name, dep_id) values ('Alex', 3);
insert into employees(name, dep_id) values ('Tom', 2);
insert into employees(name, dep_id) values ('Stan', 1);
insert into employees(name) values ('Nick');
insert into employees(name, dep_id) values ('David', 1);

--2. Выполнить запросы с left, rigth, full, cross соединениями
select * from employees left join departments d on employees.dep_id = d.id;
select * from employees right join departments d on employees.dep_id = d.id;
select * from employees full join departments d on employees.dep_id = d.id;
select * from employees cross join departments;

--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select * from employees left join departments d on employees.dep_id = d.id where dep_id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select e.id, e.name, e.dep_id, d.id, d.name from employees as e left join departments d on e.dep_id = d.id;
select e.id, e.name, e.dep_id, d.id, d.name from departments as d right join employees e on d.id = e.dep_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
  id serial primary key,
  name varchar(30),
  gender varchar(30)
);

insert into teens(name, gender) values ('Tanya', 'female');
insert into teens(name, gender) values ('Sonya', 'female');
insert into teens(name, gender) values ('Ksenia', 'female');
insert into teens(name, gender) values ('Tom', 'male');
insert into teens(name, gender) values ('Bob', 'male');
insert into teens(name, gender) values ('Stan', 'male');

select * from teens as t cross join teens t2 where t.gender != t2.gender;




