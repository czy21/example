create table ent_sale
(
    id                    String,
    order_date            String comment '订单日期',
    from_institution_code Nullable(String) comment '卖家编码',
    from_institution_name Nullable(String) comment '卖家名称',
    to_institution_code   Nullable(String) comment '买家编码',
    to_institution_name   Nullable(String) comment '买家名称',
    product_code          Nullable(String) comment '产品代码',
    product_name          Nullable(String) comment '产品名称',
    product_spec          Nullable(String) comment '产品规格',
    product_unit          Nullable(String) comment '单位',
    product_quantity      Nullable(String) comment '数量',
    product_batch_number  Nullable(String) comment '产品批号',
    product_price         Nullable(String) comment '单价',
    product_amount        Nullable(String) comment '金额'
) ENGINE =MergeTree()
PRIMARY KEY id
PARTITION BY toYYYYMM(order_date);