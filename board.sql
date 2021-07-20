DROP TABLE board;

DROP SEQUENCE seq_board_no;

CREATE TABLE board (
        no NUMBER PRIMARY KEY,
        title VARCHAR2(500) NOT NULL,
        content VARCHAR2(4000),
        hit NUMBER,
        reg_date DATE NOT NULL,
        user_no NUMBER NOT NULL,
        FOREIGN KEY (user_no) REFERENCES users (no)
    );

CREATE SEQUENCE seq_board_no
        INCREMENT BY 1
        START WITH 1
        NOCACHE;

INSERT INTO board
    VALUES(seq_board_no.NEXTVAL, '안녕하세요', '안녕하세요 호날두입니다.', 0, sysdate, 1);
INSERT INTO board
    VALUES(seq_board_no.NEXTVAL, '안녕하세요', '안녕하세요 메시입니다.', 0, sysdate, 2);
    INSERT INTO board
    VALUES(seq_board_no.NEXTVAL, '안녕하세요', '안녕하세요 손흥민입니다.', 0, sysdate, 3);

SELECT b.no,
       title,
       content,
       u.name,
       hit,
       to_char(sysdate,'YY-MM-DD HH24:MI') reg_date
FROM board b, users u
WHERE u.no = b.user_no;

SELECT b.no,
       title,
       u.name,
       hit,
       to_char(sysdate,'YY-MM-DD HH24:MI') reg_date
FROM board b, users u
WHERE u.no = b.user_no;

commit;

SELECT b.no,
       title,
       u.name,
       hit,
       content,
       reg_date
FROM board b, users u
WHERE u.no = b.user_no
AND b.no=3;

SELECT u.name,
           hit,
           to_char(sysdate, 'YYYY-MM-DD') reg_date,
           title,
           content
From board b, users u
where u.no = b.user_no
and b.no = 3;

UPDATE board
SET hit = +1
WHERE no = 1;

SELECT *
FROM board;

COMMIT;