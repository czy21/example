set @company1 = (select c.id from ent_sys_company c where c.name = '测试公司1');
set @company2 = (select c.id from ent_sys_company c where c.name = '测试公司2');
set @company3 = (select c.id from ent_sys_company c where c.name = '测试公司3');
set @company4 = (select c.id from ent_sys_company c where c.name = '测试公司4');


INSERT INTO `ent_sys_department`(
        `id`,
        `parent_id`,
        `name`,
        `company_id`
    )VALUES(UUID(), null, '产品部1',  @company1);


INSERT INTO `ent_sys_department`(
        `id`,
        `parent_id`,
        `name`,
        `company_id`
    )VALUES(UUID(), null, '产品部2',  @company2);

INSERT INTO `ent_sys_department`(
        `id`,
        `parent_id`,
        `name`,
        `company_id`
    )VALUES(UUID(), null, '产品部3',  @company3);

INSERT INTO `ent_sys_department`(
        `id`,
        `parent_id`,
        `name`,
        `company_id`
    )VALUES(UUID(), null, '产品部4',  @company4);