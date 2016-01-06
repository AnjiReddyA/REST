USE test;
CREATE TABLE customer_users (
	user_name varchar(20) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL
);
CREATE TABLE customer_roles (
	role_name varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE customer_users_roles (
	user_name varchar(20) NOT NULL,
	role_name varchar(20) NOT NULL,
	PRIMARY KEY (user_name, role_name),
	CONSTRAINT customer_users_roles_foreign_key_1 FOREIGN KEY (user_name) REFERENCES customer_users (user_name),
	CONSTRAINT customer_users_roles_foreign_key_2 FOREIGN KEY (role_name) REFERENCES customer_roles (role_name)
);
INSERT INTO customer_users (user_name, password) VALUES ('backoffice', 'test123');
INSERT INTO customer_users (user_name, password) VALUES ('frontoffice', 'abc123');
INSERT INTO customer_roles (role_name) VALUES ('admin');
INSERT INTO customer_roles (role_name) VALUES ('operator');
INSERT INTO customer_users_roles (user_name, role_name) VALUES ('backoffice', 'admin');
INSERT INTO customer_users_roles (user_name, role_name) VALUES ('frontoffice', 'operator');
COMMIT;