-- �إ� Employee ��ƪ�
create table IF NOT EXISTS employee (
    eid integer not null auto_increment, -- �D��A���u id (�ۦ沣�ͧǸ�: 1, 2, 3, ...)
    ename text, -- ���u�m�W
    salary integer, -- ���u�~��
    createtime datetime default current_timestamp, -- ���ɮɶ�
    primary key(eid)
);

-- �إ� Job ��ƪ�
create table IF NOT EXISTS job(
    jid integer not null auto_increment, -- �D��A�u�@ id
    jname text, -- �u�@�W��
    eid integer not null, -- ���u id
    foreign key(eid) references employee(eid), -- �~�����p
    primary key(jid)
);

-- �إ� Employee �d�Ҹ��
insert into employee(ename, salary) values('John', 40000);
insert into employee(ename, salary) values('Mary', 50000);
insert into employee(ename, salary) values('Bobo', 60000);
insert into employee(ename, salary) values('Mark', 70000);

-- �إ� Job �d�Ҹ��
insert into job(jname, eid) values('Job A', 1);
insert into job(jname, eid) values('Job B', 1);
insert into job(jname, eid) values('Job C', 2);
insert into job(jname, eid) values('Job D', 2);
insert into job(jname, eid) values('Job E', 4);
insert into job(jname, eid) values('Job F', 4);
insert into job(jname, eid) values('Job G', 4);

-- SQL �d�� 1: �涰�d��
select j.jid, j.jname, j.eid, 
	   e.eid as employee_eid, e.ename as employee_ename, e.salary as employee_salary, e.createtime as employee_createtime
from job j, employee e
where j.eid = e.eid

-- SQL �d�� 2: �V���X�֬d��
select e.eid, e.ename, e.salary, e.createtime,
	   j.jid as job_jid, j.jname as job_jname, j.eid as job_eid
from employee e, job j
where e.eid = j.eid;