CREATE TABLE "ROOT"."CUSTOMER"
(
   id integer PRIMARY KEY NOT NULL,
   firstName varchar(255) NOT NULL,
   lastName varchar(255) NOT NULL,
   signupDate timestamp NOT NULL
)
;
CREATE UNIQUE INDEX __SYS_IDX_ID_171 ON "ROOT"."CUSTOMER"(id)
;
CREATE TABLE "ROOT"."USER"
(
   id integer PRIMARY KEY NOT NULL,
   firstName varchar(255) NOT NULL,
   lastName varchar(255) NOT NULL,
   signupDate timestamp NOT NULL
)
;
CREATE UNIQUE INDEX __SYS_IDX_ID_172 ON "ROOT"."USER"(id)
;