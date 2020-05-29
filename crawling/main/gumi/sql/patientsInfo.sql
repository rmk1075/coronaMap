show tables
  from corona_gumi;
  
use corona_gumi;

create table patientsInfo (
	idx int primary key,
    age int,
    gender varchar(10),
    address varchar(50),
    lat varchar(20),
    lng varchar(20)
);

select * from patientsinfo;