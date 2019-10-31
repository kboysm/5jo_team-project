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