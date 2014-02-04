INSERT INTO customer(firstname, lastname, signupdate) values ('Juergen','Hoeller', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Mark','Fisher', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Rod','Johnson', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('David','Syer', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Gunnar','Hillert', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Dave','McCrory', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Josh','Long', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Patrick','Chanezon', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Andy','Piper', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Eric','Bottard', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Chris','Richardson', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Raja','Rao', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Rajdeep','Dua', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Monica','Wilkinson', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Mark','Pollack', NOW() );


Insert into USERS (USER_ID,USER_NAME,PASSWORD,ENABLED) values
('admin','Admin.','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',true);
Insert into USERS (USER_ID,USER_NAME,PASSWORD,ENABLED) values
('user','User','04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb',true);
Insert into ROLES (AUTHORITY,ROLE_NAME) values ('ROLE_ADMIN','Administrator Role');
Insert into ROLES (AUTHORITY,ROLE_NAME) values ('ROLE_USER','Public user');
Insert into ROLES (AUTHORITY,ROLE_NAME) values ('ROLE_RESTRICTED','Restricted user');
Insert into AUTHORITIES (USER_ID,AUTHORITY) values ('admin','ROLE_ADMIN');
Insert into AUTHORITIES (USER_ID,AUTHORITY) values ('user','ROLE_USER');
Insert into ROLES_HIERARCHY (PARENT_ROLE,CHILD_ROLE) values ('ROLE_RESTRICTED','ROLE_USER');
Insert into ROLES_HIERARCHY (PARENT_ROLE,CHILD_ROLE) values ('ROLE_USER','ROLE_ADMIN');
