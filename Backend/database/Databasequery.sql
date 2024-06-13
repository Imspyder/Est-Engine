

-- Database creation

create database estimation_engine;

-- Using database
use estimation_engine;

-- Tables will be automatically created by JPA

create table application (app_id integer not null auto_increment, app_name varchar(255), conversion double precision not null, total double precision not null, uplift varchar(255), primary key (app_id)) engine=InnoDB;
create table appsize (app_size_id integer not null auto_increment, app_size varchar(255), primary key (app_size_id)) engine=InnoDB;
create table appsize_scopes_tbl (id integer not null auto_increment, app_size_effort integer not null, app_size_id integer, scope_id integer, primary key (id)) engine=InnoDB;
create table customer (customer_id integer not null auto_increment, customer_name varchar(255), primary key (customer_id)) engine=InnoDB;
create table customer_scope (customer_id integer not null, scope_id integer not null) engine=InnoDB;
create table resource (resource_id integer not null auto_increment, count integer not null, grade varchar(255), hourly_rate_card double precision not null, rate_per_hour double precision not null, skill_set varchar(255), app_id integer, primary key (resource_id)) engine=InnoDB;
create table scope (scope_id integer not null auto_increment, scope varchar(255), primary key (scope_id)) engine=InnoDB;
alter table appsize_scopes_tbl add constraint FKle4ghc4tk6e931nahl64kw97n foreign key (app_size_id) references appsize (app_size_id);
alter table appsize_scopes_tbl add constraint FK13a4ehwrdob830buh3bp4sg0g foreign key (scope_id) references scope (scope_id);
alter table customer_scope add constraint FKk5mjbalseh5gjv8s0ao72b11m foreign key (scope_id) references scope (scope_id);
alter table customer_scope add constraint FKoh6wiufm0qknyy0p2h538b1eb foreign key (customer_id) references customer (customer_id);
alter table resource add constraint FK3ned3ls17gmfeqaobenjkhybn foreign key (app_id) references application (app_id);

-- Resource table

INSERT INTO estimation_engine.application (`app_id`,`app_name`,`conversion`,`total`,`uplift`) VALUES (1,'Sample',85,11,'1');
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (1,2,'G6',85,1,'Front End - Java Developers',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (2,2,'G6',85,1,'Backend Developers',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (3,1,'G6',85,1,'DBAs',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (4,1,'G9',85,1,'Application Architect',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (5,1,'G9',85,1,'Cloud Architect',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (6,1,'G6',85,1,'DevOps Engineer',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (7,1,'G7',85,1,'Scrum Master',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (8,1,'G6',85,1,'Application Tester',1);
INSERT INTO estimation_engine.resource (`resource_id`,`count`,`grade`,`hourly_rate_card`,`rate_per_hour`,`skill_set`,`app_id`) VALUES (9,1,'G6',85,1,'Cloud Security',1);

-- Appsize

insert into estimation_engine.appsize values(1,'XS');
insert into estimation_engine.appsize values(2,'S');
insert into estimation_engine.appsize values(3,'M');
insert into estimation_engine.appsize values(4,'L');
insert into estimation_engine.appsize values(5,'XL');

-- Scope

insert into estimation_engine.scope values(1,'Application Modernisation');
insert into estimation_engine.scope values(2,'Storage Migration');
insert into estimation_engine.scope values(3,'DB Migration');
insert into estimation_engine.scope values(4,'DB Modernisation');
insert into estimation_engine.scope values(5,'DevOps');

-- Scope appsize mapping

insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(1,1,1,3);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(2,1,2,4);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(3,1,3,6);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(4,1,4,11);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(5,1,5,17);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(6,2,1,1);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(7,2,2,2);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(8,2,3,9);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(9,2,4,14);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(10,2,5,18);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(11,3,1,4);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(12,3,2,6);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(13,3,3,10);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(14,3,4,15);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(15,3,5,31);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(16,4,1,1);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(17,4,2,3);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(18,4,3,4);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(19,4,4,8);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(20,4,5,11);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(21,5,1,1);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(22,5,2,2);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(23,5,3,2);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(24,5,4,3);
insert into estimation_engine.appsize_scopes_tbl(id,scope_id,app_size_id,app_size_effort) values(25,5,5,3);