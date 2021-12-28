CREATE TABLE ent_sale  (
  id varchar(36)  NOT NULL,
  order_date datetime null default NULL ,
  from_institution_code varchar(100) NULL DEFAULT NULL ,
  from_institution_name varchar(100) NULL DEFAULT NULL ,
  to_institution_code varchar(100) NULL DEFAULT NULL ,
  to_institution_name varchar(100) NULL DEFAULT NULL ,
  product_code varchar(100) NULL DEFAULT NULL ,
  product_name varchar(100) NULL DEFAULT NULL ,
  product_spec varchar(100) NULL DEFAULT NULL ,
  product_unit varchar(100) NULL DEFAULT NULL ,
  product_quantity decimal(19,6) NULL DEFAULT NULL ,
  product_batch_number varchar(100) NULL DEFAULT NULL ,
  product_price decimal(19,6) NULL DEFAULT NULL ,
  product_amount decimal(19,6) NULL DEFAULT NULL ,
  ${{{TrackedColumns}}},
  PRIMARY KEY ([id])
);