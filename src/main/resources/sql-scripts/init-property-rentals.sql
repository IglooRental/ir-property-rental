--DROP TABLE IF EXISTS "propertyrentals";
--CREATE TABLE IF NOT EXISTS "propertyrentals" ("id" VARCHAR(128) PRIMARY KEY, "property_id" VARCHAR(256) NOT NULL, "renter_id" VARCHAR(128) NOT NULL, "date" VARCHAR(128) NOT NULL, "duration" INTEGER NOT NULL);
INSERT INTO "propertyrentals" ("id", "property_id", "renter_id", "date", "duration") VALUES ('0', '1', '1', '2017-01-01', 3);
INSERT INTO "propertyrentals" ("id", "property_id", "renter_id", "date", "duration") VALUES ('1', '2', '2', '2018-01-04', 23);
