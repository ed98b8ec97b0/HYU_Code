/* 초기화 */
@ c:\users\hoyeo\database\droptable.sql
@ c:\users\hoyeo\database\schema.sql
@ c:\users\hoyeo\database\product.sql
@ c:\users\hoyeo\database\pc.sql
@ c:\users\hoyeo\database\printer.sql
@ c:\users\hoyeo\database\laptop.sql
set echo on
set line 300
set pagesize 100

/* 3번 */
select sum(price)
from ((select price
from PC
where model = 1005)
union
(select price
from Printer
where model = 3003));

/* 5번 */
select distinct maker
from Product
where type = 'pc'
and maker in
(select maker
from Product
where type = 'printer');

/* 8번 */
select model, speed
from PC natural join Product
where maker = 'B'
and speed > some
(select speed
from PC natural join Product
where maker = 'A');

/* 11번 */
select * from laptop;
update laptop
set screen = 
(select screen
from laptop
where speed = 100)
where model = 2008;
select * from laptop;

/* 14번 */
select * from PC;
delete from PC
where cd in 
(select cd
from PC
where cd like '%8x%');
select * from PC;