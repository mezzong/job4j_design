insert into role(role) values ('Admin');
insert into role(role) values ('User');
insert into role(role) values ('Guest');

insert into users(name, age, role_id) values ('Gleb', 25, 1);
insert into users(name, age, role_id) values ('Alex', 24, 2);
insert into users(name, age, role_id) values ('Tom', 23, 3);

insert into rules(name) values ('Read');
insert into rules(name) values ('Write');

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 2);
insert into role_rules(role_id, rules_id) values (2, 1);
insert into role_rules(role_id, rules_id) values (3, 1);

insert into category(category) values ('Service');
insert into category(category) values ('Repair');

insert into state(state) values ('Queue');
insert into state(state) values ('In progress');
insert into state(state) values ('Completed');

insert into item(subject, description, user_id, category_id, state_id) values ('Ticket 1', 'Desc 1', 1, 1, 1);
insert into item(subject, description, user_id, category_id, state_id) values ('Ticket 2', 'Desc 2', 2, 1, 1);
insert into item(subject, description, user_id, category_id, state_id) values ('Ticket 2', 'Desc 2', 3, 2, 1);

insert into comments(comment, item_id) values ('Comment 1', 1);
insert into comments(comment, item_id) values ('Comment 2', 2);
insert into comments(comment, item_id) values ('Comment 3', 3);
