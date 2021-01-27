create table department(
  id serial primary key,
  name varchar(30)
);
create table company(
  id serial primary key,
  name varchar(30),
  age int,
  address varchar(30),
  salary int,
  dept_id int references department(id)
);

insert into department(name) values ('IT Billing');
insert into department(name) values ('Finance');
insert into department(name) values ('Engineering');

insert into company(name, age, address, salary, dept_id) values ('Mark', 32, 'Moscow', 20000, 2);
insert into company(name, age, address, salary, dept_id) values ('David', 25, 'New-York', 15000, 2);
insert into company(name, age, address, salary, dept_id) values ('Kim', 23, 'Paris', 45000, 1);
insert into company(name, age, address, salary, dept_id) values ('James', 24, 'London', 65000, 3);

select * from company join department on company.dept_id = department.id;
select * from company as c join department as d on c.dept_id = d.id;
select c.age as Возраст, c.name as Имя, d as Отдел from company as c join department as d on c.dept_id = d.id;
