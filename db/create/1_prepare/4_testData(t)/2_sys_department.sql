set @company = (select c.id from ent_sys_company c where c.name = '测试公司');


INSERT INTO `ent_sys_department`(
        `id`,
        `parent_id`,
        `name`,
        `company_id`
    )VALUES( UUID(), null, '产品部',  @company);

INSERT INTO `ent_sys_department`(
        `id`,
        `parent_id`,
        `name`,
        `company_id`
    )
VALUES( UUID(), null, '市场部',  @company);