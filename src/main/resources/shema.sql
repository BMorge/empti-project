CREATE TABLE USERS (
        USERNAME VARCHAR(10) NOT NULL,
        PASSWORD VARCHAR(30) NOT NULL,
        ENABLED SMALLINT DEFAULT '1',
        PRIMARY KEY (USERNAME)
);

CREATE TABLE AUTHORITIES (
        USERNAME VARCHAR(10) NOT NULL,
        AUTHORITY VARCHAR(30) NOT NULL,
        FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME)
);

insert into USERS (USERNAME, PASSWORD) values ('user','us');
insert into AUTHORITIES (USERNAME, AUTHORITY) values ('user','ROLE_USER');

insert into USERS (USERNAME, PASSWORD) values ('admin','ad');
insert into AUTHORITIES (USERNAME, AUTHORITY) values ('admin','ROLE_ADMIN');

insert into USERS (USERNAME, PASSWORD) values ('zero','zero');
insert into AUTHORITIES (USERNAME, AUTHORITY) values ('zero','ROLE_USER, ROLE_ADMIN');