create database if not exists `questionnaire`;
use `questionnaire`;

drop table if exists `answer`;
drop table if exists `question`;
drop table if exists `form`;
drop table if exists `user`;

# define tables
create table if not exists `user`(
   `uid` int unsigned auto_increment,
   `username` varchar(20) not null,
   `password` varchar(20) not null,
   `email` varchar(50) not null,
   primary key(`uid`),
   unique(`email`)
)engine=innodb default charset=utf8;

create table if not exists `form`(
   `fid` int unsigned auto_increment,
   `uid` int unsigned not null,
   `num_questions` int unsigned not null,   
   primary key(`fid`),
   foreign key(`uid`) references `user`(`uid`)
)engine=innodb default charset=utf8;

create table if not exists `question`(
   `qid` int unsigned auto_increment,
   `fid` int unsigned not null,
   `type` varchar(10) not null,
   `no` int unsigned not null,
   primary key(`qid`),
   foreign key(`fid`) references `form`(`fid`)
)engine=innodb default charset=utf8;

create table if not exists `answer`(
   `qid` int unsigned not null,
   `no` int unsigned not null,
   `text` varchar(160) not null,    # 最多50个汉字
   foreign key(`qid`) references `question`(`qid`),
   unique(`qid`, `no`)
)engine=innodb default charset=utf8;

# init
insert into user (`username`, `password`, `email`)
values
('admin', '123456', 'admin');

# test
select * from `user`;