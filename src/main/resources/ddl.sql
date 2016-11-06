
CREATE TABLE CUSTOMERS(
   ID   INT              NOT NULL,
   NAME VARCHAR (20)     NOT NULL,
   AGE  INT              NOT NULL,
   ADDRESS  CHAR (25) ,
   SALARY   DECIMAL (18, 2),
   PRIMARY KEY (ID)
);

INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1,"bruce",24,"shanghai",200);

create TABLE content(
  id INT(11) not null AUTO_INCREMENT ,
  title varchar(100) not null,
  source_content TEXT not null,
  html_content TEXT not null,
  updatetime DATETIME not null,
  addtime DATETIME not null,
  PRIMARY KEY (id),
  INDEX addtime (addtime)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8