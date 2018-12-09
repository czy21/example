insert into sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
values(UUID(),'00000000-0000-0000-0000-000000000000','系统管理','erp-icon-system',NULL,'#',1,'用于系统管理的菜单',NOW(),NOW(),NULL,NULL);

set @parent_id=(select menu_id from sys_menu where menu_name='系统管理');

INSERT INTO sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
VALUES (UUID(), @parent_id, '用户管理', 'erp-icon-usermanage', 0, '/system/user',1, '用于用户管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
VALUES (UUID(), @parent_id, '角色管理', 'erp-icon-role', 0, '/system/role',1, '用于角色管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
VALUES (UUID(), @parent_id, '菜单管理', 'erp-icon-menu', 0, '/system/menu',1, '用于菜单管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
VALUES (UUID(), @parent_id, '组织管理', 'erp-icon-organization', 0, '#',1, '用于组织管理的菜单', NOW(), NOW(), NULL, NULL);

set @organized_id=(select menu_id from sys_menu where menu_name='组织管理');

INSERT INTO sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
VALUES (UUID(), @organized_id, '部门管理', 'erp-icon-department', 0, '/system/department',1, '用于部门管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(menu_id, parent_id, menu_name, icon, sort, url, is_menu, remark,added_time,modified_time,added_user,modified_user)
VALUES (UUID(), @organized_id, '公司管理', 'erp-icon-company', 0, '/system/company',1, '用于公司管理的菜单', NOW(), NOW(), NULL, NULL);
