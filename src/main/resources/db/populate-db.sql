delete from departments;
delete from lectors;
delete from departments_lectors;
delete from degrees;
alter sequence global_seq restart with 1;

insert into degrees(id, name) values
(1, 'assistant'),
(2, 'associate professor'),
(3, 'professor');

insert into lectors (name, degree_id, salary) values
('Margaret Mead', 1, 1000),
('Peter Boyle', 2, 3000),
('Alan Turing', 3, 5000);

insert into departments (name, head_id) values
('Anthropology', 1),
('Epidemiology', 2),
('Mathematics', 3);

insert into departments_lectors(department_id, lector_id) values
(1,1),
(1,2),
(2,1),
(2,2),
(2,3),
(3,3);