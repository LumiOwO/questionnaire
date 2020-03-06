create database if not exists `questionnaire`;
use `questionnaire`;

drop table if exists `user`;
drop table if exists `form`;
drop table if exists `question`;
drop table if exists `answer`;

# define tables
create table if not exists `user`(
   `uid` int unsigned auto_increment,
   `username` varchar(20) not null,
   `password` varchar(20) not null,
   `email` varchar(50) not null default 'admin',
   primary key(`uid`),
   unique(`email`)
)engine=innodb default charset=utf8;

create table if not exists `form`(
   `fid` int unsigned auto_increment,
   primary key(`fid`)
)engine=innodb default charset=utf8;

create table if not exists `question`(
   `qid` int unsigned auto_increment,
   primary key(`qid`)
)engine=innodb default charset=utf8;

create table if not exists `answer`(
   `aid` int unsigned auto_increment,
   primary key(`aid`)
)engine=innodb default charset=utf8;

# test
insert into user (`username`, `password`)
values
('admin', '123456');

select * from `user`;