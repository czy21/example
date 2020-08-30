--产品分类表
CREATE TABLE [dbo].[ent_data_product_category] (
  [product_category_id] varchar(36) PRIMARY KEY NOT NULL,
  [name] varchar(100) NOT NULL,
  [parent_product_category_id] varchar(36) NULL
)
GO

--产品表
CREATE TABLE [dbo].[ent_data_product] (
  [product_id] varchar(36) PRIMARY KEY NOT NULL,
  [parent_product_id] varchar(36) NULL,
  [product_name] varchar(100) NOT NULL,
  [product_category_id] varchar(36) NOT NULL
)  
GO

ALTER TABLE [dbo].[ent_data_product]
ADD CONSTRAINT [fk_Product_Category] FOREIGN KEY ([product_category_id])
REFERENCES [dbo].[ent_data_product_category] ([product_category_id]) 
ON DELETE NO ACTION ON UPDATE NO ACTION;


--产品属性key表
CREATE TABLE [dbo].[ent_data_product_attribute_key] (
  [product_attribute_key_id] varchar(36) PRIMARY KEY NOT NULL,
  [attribute_name] varchar(100) NULL
)
GO


--产品属性value表
CREATE TABLE [dbo].[ent_data_product_attribute_value] (
  [product_attribute_value_id] varchar(36) PRIMARY KEY NOT NULL,
  [product_attribute_key_id] varchar(36) NOT NULL,
  [attribute_value] varchar(100) NULL
)
GO

ALTER TABLE [dbo].[ent_data_product_attribute_value]
ADD CONSTRAINT [fk_AttributeValue_AttributeKey] FOREIGN KEY ([product_attribute_key_id])
REFERENCES [dbo].[ent_data_product_attribute_key] ([product_attribute_key_id]) 
ON DELETE NO ACTION ON UPDATE NO ACTION;


--产品属性表
CREATE TABLE [dbo].[ent_data_product_attribute] (
  [product_attribute_id] varchar(36) PRIMARY KEY NOT NULL,
  [product_id] varchar(36) NOT NULL,
  [product_attribute_key_id] varchar(36) NULL,
  [product_attribute_value_id] varchar(36) NULL
)
GO


ALTER TABLE [dbo].[ent_data_product_attribute]
ADD CONSTRAINT [fk_ProductAttribute_Product] FOREIGN KEY ([product_id])
REFERENCES [dbo].[ent_data_product] ([product_id])
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [dbo].[ent_data_product_attribute]
ADD CONSTRAINT [fk_ProductAttribute_AttributeKey] FOREIGN KEY ([product_attribute_key_id])
REFERENCES [dbo].[ent_data_product_attribute_key] ([product_attribute_key_id])
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [dbo].[ent_data_product_attribute]
ADD CONSTRAINT [fk_ProductAttribute_AttributeValue] FOREIGN KEY ([product_attribute_value_id])
REFERENCES [dbo].[ent_data_product_attribute_value] ([product_attribute_value_id])
ON DELETE NO ACTION ON UPDATE NO ACTION;


--产品SKU信息表
CREATE TABLE [dbo].[ent_data_product_sku] (
  [product_sku_id] varchar(36) PRIMARY KEY NOT NULL,
  [product_id] varchar(36) NOT NULL,
  [product_price] varchar(36) NULL,
  [trade_name] varchar(100) NULL,
  [common_name] varchar(100) NULL,
  [product_attributes] varchar(36) NULL
)
GO

ALTER TABLE [dbo].[ent_data_product_sku]
ADD CONSTRAINT [fk_ProductSKU_Product] FOREIGN KEY ([product_id])
REFERENCES [dbo].[ent_data_product] ([product_id])
ON DELETE NO ACTION ON UPDATE NO ACTION;