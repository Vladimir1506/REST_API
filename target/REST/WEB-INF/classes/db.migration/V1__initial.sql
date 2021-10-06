create table users (
   "id" int NOT NULL AUTO_INCREMENT,
   "name" varchar(45) NOT NULL,
   PRIMARY KEY ("id"),
   UNIQUE KEY "id_UNIQUE" ("id")
 );
create TABLE files (
   "id" int NOT NULL AUTO_INCREMENT,
   "name" varchar(45) NOT NULL,
   "size" int DEFAULT NULL,
   "upload" datetime DEFAULT NULL,
   "path" varchar(45) DEFAULT NULL,
   "user_id" int NOT NULL,
   PRIMARY KEY ("id","user_id"),
   UNIQUE KEY "id_UNIQUE" ("id"),
   KEY "user_id_idx" ("user_id"),
   CONSTRAINT "user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("id")
 );
