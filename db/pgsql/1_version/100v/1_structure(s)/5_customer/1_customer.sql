CREATE TABLE customer  (
  id serial NOT NULL,
  name varchar(50) NOT NULL,
  gender int2 NULL,
  id_num varchar(50) NULL,
  phone_no varchar(50) NULL,
  {{ TrackColumn }},
  PRIMARY KEY (id)
);