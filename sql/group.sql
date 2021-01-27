create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Phone', 4000);
insert into devices(name, price) values ('Tablet', 7000);
insert into devices(name, price) values ('PC', 20000);

insert into people(name) values ('Tom');
insert into people(name) values ('Stan');
insert into people(name) values ('David');
insert into people(name) values ('Alex');

insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (1, 4);

select p.name, avg(d.price) from devices_people
    join devices d on devices_people.device_id = d.id
    join people p on devices_people.people_id = p.id
group by p.name;

select p.name, avg(d.price) from devices_people
    join devices d on devices_people.device_id = d.id
    join people p on devices_people.people_id = p.id
group by p.name
having avg(d.price) > 5000;