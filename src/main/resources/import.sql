INSERT INTO USERS VALUES(USER_SEQUENCE.nextval, 1, '12345', 'user');
INSERT INTO USERS VALUES(USER_SEQUENCE.nextval, 1, '12345', 'admin');
INSERT INTO ROLE VALUES(1, 'ROLE_USER', 'ROLE_USER');
INSERT INTO ROLE VALUES(2, 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO USER_ROLE VALUES (1, 1);
INSERT INTO USER_ROLE VALUES (51, 2);
INSERT INTO NEWS(ID, AUTHOR, TITLE, CONTENT,PUBLISHED_DATE) VALUES(NEWS_SEQUENCE.nextval, 'ADMIN', 'BERITA 1', 'BERITA 1 Content Content Content Content', TO_DATE('2018-01-01', 'yyyy-mm-dd'))
INSERT INTO NEWS(ID, AUTHOR, TITLE, CONTENT,PUBLISHED_DATE) VALUES(NEWS_SEQUENCE.nextval, 'ADMIN', 'BERITA 2', 'Berita 2 Content Content Content Content', TO_DATE('2018-01-01', 'yyyy-mm-dd'))
INSERT INTO NEWS(ID, AUTHOR, TITLE, CONTENT,PUBLISHED_DATE) VALUES(NEWS_SEQUENCE.nextval, 'ADMIN', 'BERITA 3', 'Berita 3 Content Content Content Content', TO_DATE('2018-01-01', 'yyyy-mm-dd'))
INSERT INTO NEWS(ID, AUTHOR, TITLE, CONTENT,PUBLISHED_DATE) VALUES(NEWS_SEQUENCE.nextval, 'ADMIN', 'BERITA 4', 'Berita 4 Content Content Content Content', TO_DATE('2018-01-01', 'yyyy-mm-dd'))
INSERT INTO NEWS(ID, AUTHOR, TITLE, CONTENT,PUBLISHED_DATE) VALUES(NEWS_SEQUENCE.nextval, 'ADMIN', 'BERITA 5', 'Berita 5 Content Content Content Content', TO_DATE('2018-01-01', 'yyyy-mm-dd'))
INSERT INTO NEWS(ID, AUTHOR, TITLE, CONTENT,PUBLISHED_DATE) VALUES(NEWS_SEQUENCE.nextval, 'ADMIN', 'BERITA 6', 'Berita 6 Content Content Content Content', TO_DATE('2018-01-01', 'yyyy-mm-dd'))