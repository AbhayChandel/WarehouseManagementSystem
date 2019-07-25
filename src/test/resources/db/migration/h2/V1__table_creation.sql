CREATE SEQUENCE user_authentication_seq;

CREATE TABLE user_authentication(
id BIGINT default user_authentication_seq.nextval NOT NULL,
username varchar(100) NOT NULL,
password varchar(100) NOT NULL,
CONSTRAINT Username_Unique UNIQUE (username),
CONSTRAINT PK_Worker PRIMARY KEY(id)
);