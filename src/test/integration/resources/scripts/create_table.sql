DROP TABLE customers
IF EXISTS;

CREATE TABLE customers
(
  id         INT IDENTITY PRIMARY KEY NOT NULL,
  surname    VARCHAR(45)              NOT NULL,
  name       VARCHAR(45)              NOT NULL,
  patronymic VARCHAR(45)              NOT NULL,
  age        INT                      NOT NULL
);
CREATE UNIQUE INDEX id_UNIQUE ON customers (id);
