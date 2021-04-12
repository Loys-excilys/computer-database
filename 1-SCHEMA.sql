drop schema if exists `computer-database-db`;
  create schema if not exists `computer-database-db`;
  use `computer-database-db`;

  drop table if exists computer;
  drop table if exists company;
  drop table if exists authorities;
  drop table if exists users;

  create table company (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    logo		      varchar(255);
    constraint pk_company primary key (id))
  ;

  create table computer (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    introduced                timestamp NULL,
    discontinued              timestamp NULL,
    company_id                bigint default NULL,
    constraint pk_computer primary key (id))
  ;

  create table authorities (
    id			      bigint not null auto_increment,
    authority		      varchar(255) not null,
    primary key (id))
  ;

  create table users (
    id 			      bigint not null auto_increment,
    username		      varchar(50) not null,
    password		      varchar(100) not null,
    enabled		      tinyint(4) not null default 1,
    authority_id	      bigint not null,
    primary key (id),
    foreign key (authority_id) references authorities(id))
  ;

  alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (company_id);



