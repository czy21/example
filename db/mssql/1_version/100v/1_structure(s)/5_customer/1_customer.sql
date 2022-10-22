CREATE TABLE customer  (
  id bigint IDENTITY(1,1),
  name varchar(50) NOT NULL,
  gender tinyint NULL,
  id_num varchar(50) NULL,
  phone_no varchar(50) NULL,
  {{ TrackColumn }},
  PRIMARY KEY (id)
);