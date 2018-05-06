prompt CREATE NEW EMPLOYEE RECORD
prompt
prompt Enter the employee''s information:
prompt
accept my_ename char format a10 prompt 'Last name: '
accept my_empno number format '9999' prompt 'Employee #: '
accept my_sal number format '99999.99' default '1000.00' prompt 'Salary [1000]: '
accept my_comm number format '9999.99' default '0' prompt 'Commission % [0]: '
accept my_hiredate date format 'mm/dd/yyyy' prompt 'Hire Date (mm/dd/yyyy): '

prompt List of avarilable jobs:
select distinct job from emp order by job asc;
accept my_job char prompt 'Job: '
prompt
prompt List of department numbers and name:
select distinct deptno, dname from dept order by deptno asc;
accept my_deptno number prompt 'Department #: '
insert into emp (empno, ename, job, hiredate, sal, comm, deptno)
values (&my_empno, '&my_ename', '&my_job', to_date('&my_hiredate', 'mm/dd/yyyy'), &my_sal, &my_comm, &my_deptno)
/
