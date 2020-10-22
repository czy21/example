set @dept_a = (select d.id from ent_sys_department d where d.name = '产品部1');
set @dept_b = (select d.id from ent_sys_department d where d.name = '产品部2');
set @dept_c = (select d.id from ent_sys_department d where d.name = '产品部3');
set @dept_d = (select d.id from ent_sys_department d where d.name = '产品部4');

INSERT INTO `ent_sys_user`(`id`, 
                           `account`, 
                           `password`, 
                           `user_name`, 
                           `department_id`) 
VALUES (uuid(), 'zhangsan', 'zhangsan', '张三', @dept_a);

INSERT INTO `ent_sys_user`(`id`, 
                           `account`, 
                           `password`, 
                           `user_name`, 
                           `department_id`) 
VALUES (uuid(), 'wangwu', 'wangwu', '王五', @dept_b);


INSERT INTO `ent_sys_user`(`id`, 
                           `account`, 
                           `password`, 
                           `user_name`, 
                           `department_id`) 
VALUES (uuid(), 'lisi', 'lisi', '李四', @dept_c);

INSERT INTO `ent_sys_user`(`id`, 
                           `account`, 
                           `password`, 
                           `user_name`, 
                           `department_id`) 
VALUES (uuid(), 'sunliu', 'sunliu', '孙六', @dept_d);