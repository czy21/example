
CREATE TABLE ent_sale  (
  id varchar(36)  NOT NULL,
  order_date timestamp (6) NULL DEFAULT NULL,
  from_institution_code varchar(100)  NULL DEFAULT NULL ,
  from_institution_name varchar(100)  NULL DEFAULT NULL ,
  to_institution_code varchar(100)  NULL DEFAULT NULL ,
  to_institution_name varchar(100)  NULL DEFAULT NULL ,
  product_code varchar(100)  NULL DEFAULT NULL ,
  product_name varchar(100)  NULL DEFAULT NULL ,
  product_spec varchar(100)  NULL DEFAULT NULL ,
  product_unit varchar(100)  NULL DEFAULT NULL ,
  product_quantity varchar(100) NULL DEFAULT NULL ,
  product_batch_number varchar(100)  NULL DEFAULT NULL ,
  product_price varchar(100) NULL DEFAULT NULL ,
  product_amount varchar(100) NULL DEFAULT NULL ,
  {{ TrackColumn }},
  PRIMARY KEY (id)
 );

COMMENT ON TABLE public.ent_sale is '销售';
COMMENT ON COLUMN public.ent_sale.order_date is '订单日期';
COMMENT ON COLUMN public.ent_sale.from_institution_code is '经销商代码';
COMMENT ON COLUMN public.ent_sale.from_institution_name is '经销商名称';
COMMENT ON COLUMN public.ent_sale.to_institution_code is '客户代码';
COMMENT ON COLUMN public.ent_sale.to_institution_name is '客户名称';
COMMENT ON COLUMN public.ent_sale.product_code is '产品代码';
COMMENT ON COLUMN public.ent_sale.product_name is '产品名称';
COMMENT ON COLUMN public.ent_sale.product_spec is '产品规格';
COMMENT ON COLUMN public.ent_sale.product_unit is '单位';
COMMENT ON COLUMN public.ent_sale.product_quantity is '数量';
COMMENT ON COLUMN public.ent_sale.product_batch_number is '产品批号';
COMMENT ON COLUMN public.ent_sale.product_price is '单价';
COMMENT ON COLUMN public.ent_sale.product_amount is '金额';