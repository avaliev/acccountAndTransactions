CREATE TABLE Person (
  id   BIGINT auto_increment,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE Account (
  id        BIGINT auto_increment,
  balans    decimal,
  person_id BIGINT ,
  PRIMARY KEY (id),
  FOREIGN KEY (person_id) REFERENCES Person (id)
);
CREATE TABLE Transfer (
  id             BIGINT auto_increment,
  outcomeAccount BIGINT ,
  incomeAccount  BIGINT ,
  datetime       TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (outcomeAccount) REFERENCES Account(id),
  FOREIGN KEY (incomeAccount ) REFERENCES Account(id)
);
