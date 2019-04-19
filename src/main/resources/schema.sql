create table planets
(
   id integer not null auto_increment,
   node varchar(2) not null,
   name varchar(255) not null,
   primary key(id)
);

create table routes
(
   id integer not null auto_increment,
   origin varchar(5) not null,
   destination varchar(5) not null,
   distance decimal(5,2) not null,
   traffic decimal(5,2),
   primary key(id)
);