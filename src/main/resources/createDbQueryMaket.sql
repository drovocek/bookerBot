DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS user_expens;
DROP TABLE IF EXISTS user_income;

CREATE TABLE app_user
(
	us_id BIGINT not null,
    us_firstName VARCHAR (50),
    us_lastName VARCHAR(50),
    us_userName VARCHAR(50),
    PRIMARY KEY (us_id)
);

CREATE TABLE user_expens
(
	ex_us_id INTEGER not null,
    ex_sum BIGINT not null ,
    ex_date TIMESTAMP not null,
    ex_type VARCHAR (50) not null,
    ex_description VARCHAR (50),
    ex_cash BOOLEAN not null,
	FOREIGN KEY (ex_us_id) REFERENCES app_user(us_id) ON DELETE RESTRICT
);

CREATE TABLE user_income
(
	in_us_id INTEGER not null,
    in_sum BIGINT not null ,
    in_date DATE not null,
    in_expensType VARCHAR (50) not null,
    in_description VARCHAR (50),
    in_cash BOOLEAN not null,
	FOREIGN KEY (in_us_id) REFERENCES app_user(us_id) ON DELETE RESTRICT
);

