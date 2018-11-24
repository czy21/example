insert into sys_menu (MenuId,ParentId,MenuName,Icon,Sort,Url,IsMenu,Remark,AddedTime,ModifiedTime,AddedUser,ModifiedUser)
values(UUID(),'00000000-0000-0000-0000-000000000000','系统管理','erp-icon-system',NULL,'#',1,'用于系统管理的菜单',NOW(),NOW(),NULL,NULL);

set @ParentId=(select MenuId from sys_menu where MenuName='系统管理');

INSERT INTO sys_menu(MenuId, ParentId, MenuName, Icon, Sort, Url, IsMenu, Remark, AddedTime, ModifiedTime, AddedUser, ModifiedUser) 
VALUES (UUID(), @ParentId, '用户管理', 'erp-icon-usermanage', 0, '/system/user',1, '用于用户管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(MenuId, ParentId, MenuName, Icon, Sort, Url, IsMenu, Remark, AddedTime, ModifiedTime, AddedUser, ModifiedUser)
VALUES (UUID(), @ParentId, '角色管理', 'erp-icon-role', 0, '/system/role',1, '用于角色管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(MenuId, ParentId, MenuName, Icon, Sort, Url, IsMenu, Remark, AddedTime, ModifiedTime, AddedUser, ModifiedUser) 
VALUES (UUID(), @ParentId, '菜单管理', 'erp-icon-menu', 0, '/system/menu',1, '用于菜单管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(MenuId, ParentId, MenuName, Icon, Sort, Url, IsMenu, Remark, AddedTime, ModifiedTime, AddedUser, ModifiedUser) 
VALUES (UUID(), @ParentId, '组织管理', 'erp-icon-organization', 0, '#',1, '用于组织管理的菜单', NOW(), NOW(), NULL, NULL);

set @OrganizedId=(select MenuId from sys_menu where MenuName='组织管理');

INSERT INTO sys_menu(MenuId, ParentId, MenuName, Icon, Sort, Url, IsMenu, Remark, AddedTime, ModifiedTime, AddedUser, ModifiedUser) 
VALUES (UUID(), @OrganizedId, '部门管理', 'erp-icon-department', 0, '/system/department',1, '用于部门管理的菜单', NOW(), NOW(), NULL, NULL);

INSERT INTO sys_menu(MenuId, ParentId, MenuName, Icon, Sort, Url, IsMenu, Remark, AddedTime, ModifiedTime, AddedUser, ModifiedUser) 
VALUES (UUID(), @OrganizedId, '公司管理', 'erp-icon-company', 0, '/system/company',1, '用于公司管理的菜单', NOW(), NOW(), NULL, NULL);
