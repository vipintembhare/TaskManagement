 drop table DEPARTMENT;
CREATE TABLE DEPARTMENT (
  DEPT_ID decimal(2,0) NOT NULL,
  DEPT_NAME varchar(14) default NULL,
  PRIMARY KEY (DEPT_ID)
)ENGINE=INNODB;

DROP table PROJECT;
CREATE TABLE PROJECT (
  PROJECT_ID VARCHAR(30) NOT NULL,
  PROJECT_NAME varchar(14) default NULL,
  PROJECT_DESC varchar(150) default NULL,
  DEPT_ID decimal(2,0),
  PRIMARY KEY (PROJECT_ID),
  CONSTRAINT FOREIGN KEY FK_DEPT(DEPT_ID) REFERENCES DEPARTMENT(DEPT_ID)
)ENGINE=INNODB;

DROP table EMPLOYEE;
CREATE TABLE EMPLOYEE (
  EMP_ID VARCHAR(30) NOT NULL,
  EMP_NAME varchar(30) default NULL,
  DESIGNATION varchar(20) default NULL,
  EMAIL varchar(35) default NULL,
  MANAGER_ID decimal(2,0),
  DATE_OF_JOINING DATE,
  DEPT_ID decimal(2,0),
  PROJECT_ID VARCHAR(30),
  PRIMARY KEY (EMP_ID),
  CONSTRAINT FOREIGN KEY FK_DEPT(DEPT_ID) REFERENCES DEPARTMENT(DEPT_ID),
  CONSTRAINT FOREIGN KEY FK_PROJECT(PROJECT_ID) REFERENCES PROJECT(PROJECT_ID)
)ENGINE=INNODB;

DROP table TASK;
CREATE TABLE TASK (
  TASK_ID INT NOT NULL AUTO_INCREMENT,
  TASK_NAME varchar(30) default NULL,
  TASK_DESC varchar(150) default NULL,
  PRIORITY SMALLINT(2),
  START_DATE DATE,
  END_DATE DATE,
  EMP_ID VARCHAR(30),
  PROJECT_ID varchar(30),
  PRIMARY KEY (TASK_ID),
  CONSTRAINT FOREIGN KEY FK_EMP(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
  CONSTRAINT FOREIGN KEY FK_PROJECT(PROJECT_ID) REFERENCES PROJECT(PROJECT_ID)
)ENGINE=INNODB;

insert into EMPLOYEE values ('M1039999','Vipin Tembhare','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'D+H');
insert into EMPLOYEE values ('M1039998','Rahul Girish','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'D+H');
insert into EMPLOYEE values ('M1039996','Lovelyn Sharma','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'BBC');
insert into EMPLOYEE values ('M1039997','Akshay Tembhare','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'D+H');
insert into EMPLOYEE values ('M1039995','Raju Tembhare','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'BBC');
insert into EMPLOYEE values ('M1039994','Maehta Tembhare','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'Equifax');
insert into EMPLOYEE values ('M1039993','Pankaj Tembhare','Module Lead','vipintembhare@gmail.com','2','02-02-02',2,'D+H');


Insert into TASK values (1,'Deadlock resolution','Deadlock resolution',1,'02-02-02','02-02-02','M1039999','BBC');
Insert into TASK values (2,'TimeSheet','TimeSheet',1,'02-02-02','02-02-02','M1039998','BBC');
Insert into TASK values (3,'TimeSheet','TimeSheet',1,'02-02-02','02-02-02','M1039997','BBC');
Insert into TASK values (4,'API Generation','API Generation',1,'02-02-02','02-02-02','M1039997','D+H');
Insert into TASK values (5,'Deadlock resolution2','Deadlock resolution',1,'02-02-02','02-02-02','M1039999','Equifax');
Insert into TASK values (8,'TimeSheet2','TimeSheet',1,'02-02-02','02-02-02','M1039998','D+H');
Insert into TASK values (6,'TimeSheet2','TimeSheet',1,'02-02-02','02-02-02','M1039997','BBC');
Insert into TASK values (7,'API Generation2','API Generation',1,'02-02-02','02-02-02','M1039997','D+H');