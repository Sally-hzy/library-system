
CREATE DATABASE library;    /*创建数据库*/

USE library;   /*使用数据库，使之后面的建表以及添加数据能添加在此数据库中*/

/*Table structure for table `tb_bookinfo` */

DROP TABLE tb_bookinfo;  /*  如果此表格存在，则删除，此处不能使用EXISTS，在SQL  Server中会报错，说明在SQL Server 中不存在此关键字*/

--创建表格图书信息表 tb_bookinfo
CREATE TABLE tb_bookinfo (
   ISBN varchar(20) DEFAULT NULL,		--图书编号
   typeid  varchar(20) DEFAULT NULL,	--图书类别编号
   writer  varchar(20) DEFAULT NULL,	--作者
  translator  varchar(20) DEFAULT NULL,	--译者
   publisher  varchar(20) DEFAULT NULL,	--出版商
   date datetime DEFAULT NULL,			--出版日期
   price  smallmoney  DEFAULT NULL,		--价格
   bookname  varchar(40) DEFAULT NULL	--书名
);

/*Data for the table `tb_bookinfo` */
insert  into  tb_bookinfo ( ISBN ,typeid, writer,translator,publisher,date,price,bookname) values 
('1111111111111','2','毛以锋','哈哈','***出版社','2013-04-24 00:00:00',20,'java开发');

/*Table structure for table `tb_booktype` */

DROP TABLE  tb_booktype;
--创建表格图书类别   tb_booktype
CREATE TABLE tb_booktype (
  id int identity(1,1) NOT NULL ,		--设置自增  图书类别编号
  typeName varchar(20) DEFAULT NULL,	--图书类别名称
  days varchar(20) DEFAULT NULL,		--可借天数
  fk varchar(20) DEFAULT NULL,			--罚款（超过一天的罚款）
  PRIMARY KEY (id)						--图书类别编号为主键
) 

/*Data for the table `tb_booktype` */
--添加数据到图书类别表格中
insert  into tb_booktype(typeName,days,fk) values ('计算机类','30','0.1'),('新闻类','3','0.1');



DROP TABLE tb_borrow;
--创建借阅表tb_borrow
CREATE TABLE tb_borrow (
  id int PRIMARY KEY identity(1,1) NOT NULL ,	--借阅编号
  bookISBN varchar(20) DEFAULT NULL,			--图书编号
  readerISBN varchar(20) DEFAULT NULL,			--读者编号
  num varchar(20) DEFAULT NULL,					--读者可借数量
  borrowDate varchar(40) DEFAULT NULL,			--借阅时间
  backDate varchar(40) DEFAULT NULL,			--图书应当归还时间
  bookName varchar(20) DEFAULT NULL,			--书名
  operatorId varchar(20) DEFAULT NULL,			--管理员（用户）编号
  isback varchar(1) DEFAULT '1'					--图书状态（1 表示归还  0表示未归还）
 
) ;
/*Data for the table `tb_borrow` */
--添加数据到图书借阅表中
insert  into tb_borrow(bookISBN,readerISBN,num,borrowDate,backDate,bookName,operatorId,isback) values 
('1111111111111','1111111111111',NULL,'2013-04-25 02:53:34.0','2013-04-25 02:53:34.0',NULL,'1','0'),
('1111111111111','1111111111111',NULL,'2013-04-25 02:59:10.0','2013-04-28 02:59:10.0',NULL,'1',NULL),
('1111111111111','1111111111111',NULL,'2013-04-25 03:00:52.0','2013-04-28 03:00:52.0',NULL,'1','0');

/*Table structure for table `tb_operator` */

DROP TABLE tb_operator;
--创建表格用户表即管理员表格tb_operator
CREATE TABLE tb_operator(
  id int  identity(1,1) NOT NULL,   --用户编号 设置自增
  name varchar(20) DEFAULT NULL,    --姓名
  sex varchar(20) DEFAULT NULL,		--性别
  age int DEFAULT NULL,				--年龄
  identityCard varchar(50) DEFAULT NULL,		--身份证号
  workdate datetime DEFAULT NULL,				--办证日期
  tel varchar(20) DEFAULT NULL,					--电话
  admin int DEFAULT '0',						--权限  设置默认为0
  password varchar(20) DEFAULT NULL,			--密码
  PRIMARY KEY (id)								--id为主键				
) 

--添加一组数据到tb_operator中
insert  into tb_operator(id,name,sex,age,identityCard,workdate,tel,password) values
 (1,'java1234',NULL,NULL,NULL,NULL,'caofen');


DROP TABLE tb_orders;
--创建表格购买书籍  tb_orders 
CREATE TABLE tb_orders (
  ISBN varchar(20) DEFAULT NULL,	--图书编号
  date datetime DEFAULT NULL,		--购买时间
  number varchar(20) DEFAULT NULL,	--购买数量
  operator varchar(20) DEFAULT NULL,	--管理员
  checkAndAccept varchar(20) DEFAULT NULL,	--验收状态（是或否）	
  zk varchar(20) DEFAULT NULL				--折扣
) 

/*Data for the table `tb_order` */

/*购买数据清单*/
insert  into tb_orders(ISBN,date,number,operator,checkAndAccept,zk) values ('1111111111111','2013-04-25 00:00:00','11','java1234','0','0.1');

/*Table structure for table `tb_reader` */

DROP TABLE tb_reader;
/*创建读者用户表*/
CREATE TABLE tb_reader (
  name varchar(20) DEFAULT NULL,	--姓名
  sex varchar(20) DEFAULT NULL,		--性别
  age varchar(10) DEFAULT NULL,		--年龄
  identityCard varchar(40) DEFAULT NULL,	--身份证
  date datetime DEFAULT NULL,		--办证日期
  maxNum varchar(10) DEFAULT NULL,	--最大借书量
  tel varchar(20) DEFAULT NULL,		--电话
  keepMoney smallmoney DEFAULT NULL,	--押金
  zj int DEFAULT NULL,					--证件类别
  zy varchar(20) DEFAULT NULL,		--职业	
  ISBN varchar(20) DEFAULT NULL,--读者编号
  bztime datetime DEFAULT NULL		--证件有效期
) ;

/*Data for the table `tb_reader` */

insert  into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,ISBN,bztime) values ('张三','1','11','1111111111111','2014-04-24 00:00:00','11','11',10,3,'学生','1111111111111','2013-04-24 00:00:00');


--*************************************************************************************************************************--


--操作语句

--管理员（用户登录）
select id as 编号, name as 姓名, password as 密码,admin as 权限 from tb_operator where name='java1234 ' and password='123456'and admin='0';

--查询图书类别
select id as 编号,typeName as 名称,days as 可借天数,fk as 每天罚款  from tb_bookType;

--图书类别名查询可借的天数
select days as 可借天数  from tb_bookType where typeName='1'

--添加图书类别
insert into tb_bookType(typeName,days,fk) values('英语应用','70','0.03')

--修改图书类别
update tb_bookType set typeName='1',days='90',fk='0.05' where id='1';

--删除图书类别
delete from tb_bookType where id='1';

--查询图书类别中超过规定时间每天罚款金额以及可借天数
select fk,days  from tb_bookType where typeName='1';

--添加图书信息
insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator,publisher,date,price) values('','','','','','','','');

--查询图书全部信息
select ISBN,typeid,bookname,writer,translator,publisher,date,price from tb_bookInfo;

--根据图书编号查询图书全部信息
select ISBN,typeid,bookname,writer,translator,publisher,date,price  from tb_bookInfo where ISBN='';

--根据图书编号修改图书信息
update tb_bookInfo set ISBN='',typeId='',bookname='',writer='',translator='',publisher='',date='',price='' where ISBN='';

--删除图书信息
delete from tb_bookInfo where ISBN='';

--添加读者信息
insert into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime,ISBN) values
('12','女',21 ,'123214324','','',32,'','','','','','','');

--查询读者信息
select name,sex,age,identityCard, date,maxNum,tel,keepMoney,zj,zy,ISBN, bztime from tb_reader;

--根据读者编号查询读者信息
select name,sex, age,identityCard,date, maxNum ,tel,keepMoney,zj,zy,ISBN ,bztime  from tb_reader where ISBN='';

--修改读者信息
update tb_reader set name='',sex='',age='',identityCard='',date='" + date + "',maxNum='',tel='',keepMoney='',zj='',zy='',bztime=''where ISBN='';

--根据读者编号 删除读者信息
delete from tb_reader where ISBN='';

--购买新书
insert into tb_orders(ISBN,date,number,operator,checkAndAccept,zk) values('','', 10,'','','');

--查询新购的图书是否在图书信息表中
SELECT * FROM tb_orders a INNER JOIN tb_bookInfo b ON a.ISBN = b.ISBN;

--图书编号查询新购图书
SELECT * FROM tb_orders where ISBN='';

--更新新购图书信息  当图书验收后，更改图书信息
update tb_orders set checkAndAccept=0 where ISBN='';

-- 添加读者借阅图书信息
insert into tb_borrow(bookISBN,readerISBN,operatorId,borrowDate,backDate)values('','"','','','');

--根据读者编号查询读者借阅的图书
select *  from tb_borrow where readerISBN='';

--根据读者编号查询借阅图书归还
SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN = b.bookISBN INNER JOIN tb_reader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '' and isback=1);

--归还图书
update tb_borrow set isback=0 where bookISBN=''and readerISBN='' and id='';

--查询图书全部信息
select *  from tb_bookInfo;

--根据图书名查询图书全部信息 模糊查询
select ISBN,typeid, bookname,writer,translator,publisher,date,price from tb_bookInfo where bookname like '%"+ bookname + "%';

--根据作者查询图书的全部信息 模糊查询
select * from tb_bookInfo where writer like '%" + writer+ "%';

--添加图书管理员的信息
insert into tb_operator(name,sex,age,identityCard,workdate,tel,password) values('','',21,'','','');

--查询图书管理员的信息
select id,name,sex,age,identityCard,workdate,tel,password  from tb_operator where admin=0;

--删除图书管理员的信息
delete from tb_operator where id='';

--修改图书管理员的信息
update tb_operator set name='',sex='',age='',identityCard='',workdate=',tel=',password='' where id='';

--修改登录用户名和密码
update tb_operator set password='' where name='' and id='';

--
