#insert into users (name) values ('Lol');
select * from users;
create table tables (
   id int NOT NULL AUTO_INCREMENT,
   name varchar(45) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY id_unique (id)
 );