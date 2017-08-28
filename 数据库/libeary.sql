
CREATE DATABASE library;    /*�������ݿ�*/

USE library;   /*ʹ�����ݿ⣬ʹ֮����Ľ����Լ��������������ڴ����ݿ���*/

/*Table structure for table `tb_bookinfo` */

DROP TABLE tb_bookinfo;  /*  ����˱����ڣ���ɾ�����˴�����ʹ��EXISTS����SQL  Server�лᱨ��˵����SQL Server �в����ڴ˹ؼ���*/

--�������ͼ����Ϣ�� tb_bookinfo
CREATE TABLE tb_bookinfo (
   ISBN varchar(20) DEFAULT NULL,		--ͼ����
   typeid  varchar(20) DEFAULT NULL,	--ͼ�������
   writer  varchar(20) DEFAULT NULL,	--����
  translator  varchar(20) DEFAULT NULL,	--����
   publisher  varchar(20) DEFAULT NULL,	--������
   date datetime DEFAULT NULL,			--��������
   price  smallmoney  DEFAULT NULL,		--�۸�
   bookname  varchar(40) DEFAULT NULL	--����
);

/*Data for the table `tb_bookinfo` */
insert  into  tb_bookinfo ( ISBN ,typeid, writer,translator,publisher,date,price,bookname) values 
('1111111111111','2','ë�Է�','����','***������','2013-04-24 00:00:00',20,'java����');

/*Table structure for table `tb_booktype` */

DROP TABLE  tb_booktype;
--�������ͼ�����   tb_booktype
CREATE TABLE tb_booktype (
  id int identity(1,1) NOT NULL ,		--��������  ͼ�������
  typeName varchar(20) DEFAULT NULL,	--ͼ���������
  days varchar(20) DEFAULT NULL,		--�ɽ�����
  fk varchar(20) DEFAULT NULL,			--�������һ��ķ��
  PRIMARY KEY (id)						--ͼ�������Ϊ����
) 

/*Data for the table `tb_booktype` */
--������ݵ�ͼ���������
insert  into tb_booktype(typeName,days,fk) values ('�������','30','0.1'),('������','3','0.1');



DROP TABLE tb_borrow;
--�������ı�tb_borrow
CREATE TABLE tb_borrow (
  id int PRIMARY KEY identity(1,1) NOT NULL ,	--���ı��
  bookISBN varchar(20) DEFAULT NULL,			--ͼ����
  readerISBN varchar(20) DEFAULT NULL,			--���߱��
  num varchar(20) DEFAULT NULL,					--���߿ɽ�����
  borrowDate varchar(40) DEFAULT NULL,			--����ʱ��
  backDate varchar(40) DEFAULT NULL,			--ͼ��Ӧ���黹ʱ��
  bookName varchar(20) DEFAULT NULL,			--����
  operatorId varchar(20) DEFAULT NULL,			--����Ա���û������
  isback varchar(1) DEFAULT '1'					--ͼ��״̬��1 ��ʾ�黹  0��ʾδ�黹��
 
) ;
/*Data for the table `tb_borrow` */
--������ݵ�ͼ����ı���
insert  into tb_borrow(bookISBN,readerISBN,num,borrowDate,backDate,bookName,operatorId,isback) values 
('1111111111111','1111111111111',NULL,'2013-04-25 02:53:34.0','2013-04-25 02:53:34.0',NULL,'1','0'),
('1111111111111','1111111111111',NULL,'2013-04-25 02:59:10.0','2013-04-28 02:59:10.0',NULL,'1',NULL),
('1111111111111','1111111111111',NULL,'2013-04-25 03:00:52.0','2013-04-28 03:00:52.0',NULL,'1','0');

/*Table structure for table `tb_operator` */

DROP TABLE tb_operator;
--��������û�������Ա���tb_operator
CREATE TABLE tb_operator(
  id int  identity(1,1) NOT NULL,   --�û���� ��������
  name varchar(20) DEFAULT NULL,    --����
  sex varchar(20) DEFAULT NULL,		--�Ա�
  age int DEFAULT NULL,				--����
  identityCard varchar(50) DEFAULT NULL,		--���֤��
  workdate datetime DEFAULT NULL,				--��֤����
  tel varchar(20) DEFAULT NULL,					--�绰
  admin int DEFAULT '0',						--Ȩ��  ����Ĭ��Ϊ0
  password varchar(20) DEFAULT NULL,			--����
  PRIMARY KEY (id)								--idΪ����				
) 

--���һ�����ݵ�tb_operator��
insert  into tb_operator(id,name,sex,age,identityCard,workdate,tel,password) values
 (1,'java1234',NULL,NULL,NULL,NULL,'caofen');


DROP TABLE tb_orders;
--����������鼮  tb_orders 
CREATE TABLE tb_orders (
  ISBN varchar(20) DEFAULT NULL,	--ͼ����
  date datetime DEFAULT NULL,		--����ʱ��
  number varchar(20) DEFAULT NULL,	--��������
  operator varchar(20) DEFAULT NULL,	--����Ա
  checkAndAccept varchar(20) DEFAULT NULL,	--����״̬���ǻ��	
  zk varchar(20) DEFAULT NULL				--�ۿ�
) 

/*Data for the table `tb_order` */

/*���������嵥*/
insert  into tb_orders(ISBN,date,number,operator,checkAndAccept,zk) values ('1111111111111','2013-04-25 00:00:00','11','java1234','0','0.1');

/*Table structure for table `tb_reader` */

DROP TABLE tb_reader;
/*���������û���*/
CREATE TABLE tb_reader (
  name varchar(20) DEFAULT NULL,	--����
  sex varchar(20) DEFAULT NULL,		--�Ա�
  age varchar(10) DEFAULT NULL,		--����
  identityCard varchar(40) DEFAULT NULL,	--���֤
  date datetime DEFAULT NULL,		--��֤����
  maxNum varchar(10) DEFAULT NULL,	--��������
  tel varchar(20) DEFAULT NULL,		--�绰
  keepMoney smallmoney DEFAULT NULL,	--Ѻ��
  zj int DEFAULT NULL,					--֤�����
  zy varchar(20) DEFAULT NULL,		--ְҵ	
  ISBN varchar(20) DEFAULT NULL,--���߱��
  bztime datetime DEFAULT NULL		--֤����Ч��
) ;

/*Data for the table `tb_reader` */

insert  into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,ISBN,bztime) values ('����','1','11','1111111111111','2014-04-24 00:00:00','11','11',10,3,'ѧ��','1111111111111','2013-04-24 00:00:00');


--*************************************************************************************************************************--


--�������

--����Ա���û���¼��
select id as ���, name as ����, password as ����,admin as Ȩ�� from tb_operator where name='java1234 ' and password='123456'and admin='0';

--��ѯͼ�����
select id as ���,typeName as ����,days as �ɽ�����,fk as ÿ�췣��  from tb_bookType;

--ͼ���������ѯ�ɽ������
select days as �ɽ�����  from tb_bookType where typeName='1'

--���ͼ�����
insert into tb_bookType(typeName,days,fk) values('Ӣ��Ӧ��','70','0.03')

--�޸�ͼ�����
update tb_bookType set typeName='1',days='90',fk='0.05' where id='1';

--ɾ��ͼ�����
delete from tb_bookType where id='1';

--��ѯͼ������г����涨ʱ��ÿ�췣�����Լ��ɽ�����
select fk,days  from tb_bookType where typeName='1';

--���ͼ����Ϣ
insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator,publisher,date,price) values('','','','','','','','');

--��ѯͼ��ȫ����Ϣ
select ISBN,typeid,bookname,writer,translator,publisher,date,price from tb_bookInfo;

--����ͼ���Ų�ѯͼ��ȫ����Ϣ
select ISBN,typeid,bookname,writer,translator,publisher,date,price  from tb_bookInfo where ISBN='';

--����ͼ�����޸�ͼ����Ϣ
update tb_bookInfo set ISBN='',typeId='',bookname='',writer='',translator='',publisher='',date='',price='' where ISBN='';

--ɾ��ͼ����Ϣ
delete from tb_bookInfo where ISBN='';

--��Ӷ�����Ϣ
insert into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime,ISBN) values
('12','Ů',21 ,'123214324','','',32,'','','','','','','');

--��ѯ������Ϣ
select name,sex,age,identityCard, date,maxNum,tel,keepMoney,zj,zy,ISBN, bztime from tb_reader;

--���ݶ��߱�Ų�ѯ������Ϣ
select name,sex, age,identityCard,date, maxNum ,tel,keepMoney,zj,zy,ISBN ,bztime  from tb_reader where ISBN='';

--�޸Ķ�����Ϣ
update tb_reader set name='',sex='',age='',identityCard='',date='" + date + "',maxNum='',tel='',keepMoney='',zj='',zy='',bztime=''where ISBN='';

--���ݶ��߱�� ɾ��������Ϣ
delete from tb_reader where ISBN='';

--��������
insert into tb_orders(ISBN,date,number,operator,checkAndAccept,zk) values('','', 10,'','','');

--��ѯ�¹���ͼ���Ƿ���ͼ����Ϣ����
SELECT * FROM tb_orders a INNER JOIN tb_bookInfo b ON a.ISBN = b.ISBN;

--ͼ���Ų�ѯ�¹�ͼ��
SELECT * FROM tb_orders where ISBN='';

--�����¹�ͼ����Ϣ  ��ͼ�����պ󣬸���ͼ����Ϣ
update tb_orders set checkAndAccept=0 where ISBN='';

-- ��Ӷ��߽���ͼ����Ϣ
insert into tb_borrow(bookISBN,readerISBN,operatorId,borrowDate,backDate)values('','"','','','');

--���ݶ��߱�Ų�ѯ���߽��ĵ�ͼ��
select *  from tb_borrow where readerISBN='';

--���ݶ��߱�Ų�ѯ����ͼ��黹
SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN = b.bookISBN INNER JOIN tb_reader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '' and isback=1);

--�黹ͼ��
update tb_borrow set isback=0 where bookISBN=''and readerISBN='' and id='';

--��ѯͼ��ȫ����Ϣ
select *  from tb_bookInfo;

--����ͼ������ѯͼ��ȫ����Ϣ ģ����ѯ
select ISBN,typeid, bookname,writer,translator,publisher,date,price from tb_bookInfo where bookname like '%"+ bookname + "%';

--�������߲�ѯͼ���ȫ����Ϣ ģ����ѯ
select * from tb_bookInfo where writer like '%" + writer+ "%';

--���ͼ�����Ա����Ϣ
insert into tb_operator(name,sex,age,identityCard,workdate,tel,password) values('','',21,'','','');

--��ѯͼ�����Ա����Ϣ
select id,name,sex,age,identityCard,workdate,tel,password  from tb_operator where admin=0;

--ɾ��ͼ�����Ա����Ϣ
delete from tb_operator where id='';

--�޸�ͼ�����Ա����Ϣ
update tb_operator set name='',sex='',age='',identityCard='',workdate=',tel=',password='' where id='';

--�޸ĵ�¼�û���������
update tb_operator set password='' where name='' and id='';

--
