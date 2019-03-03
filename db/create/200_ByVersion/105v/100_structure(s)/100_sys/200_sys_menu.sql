delete sys_role_menu,sys_menu from sys_role_menu,sys_menu
where sys_role_menu.menu_id=sys_menu.menu_id and sys_menu.is_menu=0;
alter table sys_menu drop column is_menu;