DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS parkingCards;

DROP SEQUENCE IF EXISTS owner_seq;
DROP SEQUENCE IF EXISTS parkingCard_seq;

CREATE SEQUENCE owner_seq
  START 1000;
CREATE SEQUENCE parkingCard_seq
  START 5000;

CREATE TABLE owners (
  id   INTEGER PRIMARY KEY DEFAULT nextval('owner_seq'),
  name VARCHAR(100) NOT NULL
);

CREATE TABLE parkingCards (
  id       INTEGER PRIMARY KEY DEFAULT nextval('parkingCard_seq'),
  start    TIMESTAMP NOT NULL,
  finish   TIMESTAMP CHECK (finish > start),
  payCheck FLOAT               DEFAULT '2.2'
);

CREATE TABLE cars (
  carNumber     VARCHAR(10) PRIMARY KEY  NOT NULL,
  ownerId       INTEGER                  NOT NULL,
  parkingCardId INTEGER,
  FOREIGN KEY (parkingCardId) REFERENCES parkingCards (id) ON DELETE CASCADE,
  FOREIGN KEY (ownerId) REFERENCES owners (id) ON DELETE CASCADE
);