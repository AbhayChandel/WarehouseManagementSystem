CREATE SEQUENCE user_authentication_seq;

CREATE TABLE user_authentication(
id BIGINT default user_authentication_seq.nextval NOT NULL,
username varchar(100) NOT NULL,
password varchar(100) NOT NULL,
CONSTRAINT Username_Unique UNIQUE (username),
CONSTRAINT user_pk PRIMARY KEY(id)
);

CREATE SEQUENCE item_category_seq;

CREATE TABLE item_category(
id BIGINT default item_category_seq.nextval NOT NULL,
name varchar(100) NOT NULL,
CONSTRAINT unique_category_name UNIQUE (name),
CONSTRAINT item_category_pk PRIMARY KEY(id)
);

CREATE SEQUENCE item_seq;

CREATE TABLE item(
id BIGINT default item_seq.nextval NOT NULL,
name varchar(100) NOT NULL,
category BIGINT NOT NULL,
CONSTRAINT unique_item_name UNIQUE (name),
CONSTRAINT item_pk PRIMARY KEY(id)
);

CREATE TABLE fruit(
id BIGINT NOT NULL
);

CREATE TABLE vegetable(
id BIGINT NOT NULL
);