create table body(
  id serial primary key,
  name varchar(30)
);
create table engine(
  id serial primary key,
  name varchar(30),
  power int
);
create table transmission(
    id serial primary key,
    name varchar(30)
);
create table car(
    id serial primary key,
    manufacturer varchar(30),
    model varchar(30),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body values (1, 'Sedan');
insert into body values (2, 'Coupe');
insert into body values (3, 'Hatchback');
insert into body values (4, 'SUV');
insert into body values (5, 'new body');

insert into engine values (1, 'V5', 100);
insert into engine values (2, 'V6', 200);
insert into engine values (3, 'V8', 300);
insert into engine values (4, 'V12', 900);

insert into transmission values (1, 'manual');
insert into transmission values (2, 'automatic');
insert into transmission values (3, 'semi-automatic');

insert into car (manufacturer, model, body_id, engine_id, transmission_id) values ('bmw', 'm1', 1, 3, 1);
insert into car (manufacturer, model, body_id, engine_id, transmission_id) values ('bmw', 'm2', 2, 2, 2);
insert into car (manufacturer, model, body_id, engine_id, transmission_id) values ('bmw', 'm3', 3, 1, 1);

insert into car (manufacturer, model, body_id, engine_id, transmission_id) values ('mercedes', 'mm1', 1, 2, 1);
insert into car (manufacturer, model, body_id, engine_id, transmission_id) values ('mercedes', 'mm2', 3, 3, 2);
insert into car (manufacturer, model, body_id, engine_id, transmission_id) values ('mercedes', 'mm4', 4, 3, 2);

select c.id, c.manufacturer, c.model, b.name, e.name, e.power, t.name from car as c
    join body b on c.body_id = b.id
    join engine e on c.engine_id = e.id
    join transmission t on c.transmission_id = t.id;

select b.id, b.name from car as c
right join body b on c.body_id = b.id
where body_id is null;

select e.id, e.name from car as c
right join engine e on c.engine_id = e.id
where engine_id is null;

select t.id, t.name from car as c
right join transmission t on c.transmission_id = t.id
where transmission_id is null;