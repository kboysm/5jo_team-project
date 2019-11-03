
CREATE TABLE stair_shaped_board(
	no NUMBER(4) PRIMARY KEY,
	grpno NUMBER(4) NOT NULL,
	prntno NUMBER(4) REFERENCES stair_shaped_board(no) ON DELETE CASCADE,
	title VARCHAR2(200),
	writer VARCHAR2(50),
	content VARCHAR2(2000),
	regdate DATE DEFAULT SYSDATE,
	hits NUMBER(4) DEFAULT -1
);

CREATE SEQUENCE stair_shaped_board_seq;


CREATE TABLE List_Board( 
	idx NUMBER NOT NULL,
	title VARCHAR2(100) NOT NULL,
	writer VARCHAR2(30) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	reg_date  DATE DEFAULT SYSDATE,
	view_cnt NUMBER DEFAULT 0,
	PRIMARY KEY(idx)
);

create sequence List_Board_seq start with 1 increment by 1 nomaxvalue nocache; 


CREATE TABLE Gallery_Board(
    no NUMBER(4) PRIMARY KEY,
   title VARCHAR2(500),
    writer VARCHAR2(500),
   content VARCHAR2(4000),
   regdate DATE DEFAULT SYSDATE,
   image VARCHAR2(50),
   count NUMBER(10) DEFAULT 0
);

create table gallery_files(
   fno number(4) PRIMARY KEY,
   no number(4) REFERENCES Gallery_Board(no) ON DELETE CASCADE,
   filename varchar2(200),
   fileOriName varchar2(300),
   fileurl varchar2(500) 
);

CREATE TABLE file_board(
	b_no NUMBER(5) PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(1000) NOT NULL,
	writer VARCHAR2(20) NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL
);

CREATE SEQUENCE file_board_seq;


CREATE TABLE files(
	f_no NUMBER(5) PRIMARY KEY,
	b_no NUMBER(5) NOT NULL,
	filename VARCHAR2(200) NOT NULL,
	fileOriginName VARCHAR2(300) NOT NULL,
	fileUrl VARCHAR2(500) NOT NULL
);

CREATE SEQUENCE files_seq;


CREATE TABLE comments(
	c_no NUMBER(5) PRIMARY KEY,
	b_no NUMBER(5) NOT NULL,
	content VARCHAR2(300) NOT NULL,
	writer VARCHAR2(20) NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL
);

CREATE SEQUENCE comments_seq;