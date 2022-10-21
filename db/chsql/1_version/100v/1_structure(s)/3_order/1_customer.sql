CREATE TABLE customer
(

    `id` Int64,

    `name` String,

    `gender` Nullable(Int8),

    `id_num` Nullable(String),

    `phone_no` Nullable(String),

    `create_time` DateTime,

    `create_user` Nullable(String),

    `update_time` DateTime,

    `update_user` Nullable(String),

    `deleted` UInt64
)
ENGINE =MergeTree()
PRIMARY KEY id
PARTITION BY toYYYYMM(create_time);