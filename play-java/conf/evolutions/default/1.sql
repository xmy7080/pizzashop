# --- !Ups
create table user (
  username  varchar(255) not null,
  password  varchar(255) not null,
  isadmin   boolean not null default 0,
  constraint pk_user primary key (username)
);
insert into  user(username, password, isadmin) values('admin','1111',1);
insert into  user(username, password, isadmin) values('bob','2222',0);
insert into  user(username, password, isadmin) values('kate','3333',0);

create table orders (
  id    int  not null AUTO_INCREMENT,
  kind  varchar(255) not null,
  number  int not null,
  username varchar(255) not null,
  constraint pk_order primary key (id)
);
# --- !Downs
-- drop table if exists user;