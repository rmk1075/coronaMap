show tables
  from corona_gumi;
  
use corona_gumi;

create table test (
	pk varchar(20) primary key,
    test1 int,
    test2 int
);

select * from test;

insert into test values('data1', 1, 1);
insert into test values('data2', 2, 2);