# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "testModel" ("id" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL,"value" INTEGER NOT NULL);
create unique index "idxTestModel_Name" on "testModel" ("name");

# --- !Downs

drop table "testModel";

