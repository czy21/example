alter table sys_department add constraint `fk_department_company` FOREIGN KEY (`company_id`) REFERENCES `sys_company` (`company_id`) ON DELETE NO ACTION ON UPDATE RESTRICT;
alter table sys_role_menu
add CONSTRAINT `fk_role_menu_menu` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
add CONSTRAINT `fk_role_menu_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE RESTRICT;

alter table sys_user add CONSTRAINT `fk_user_department` FOREIGN KEY (`department_id`) REFERENCES `sys_department` (`department_id`) ON DELETE NO ACTION ON UPDATE RESTRICT;

alter table sys_user_role
add CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
add CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE NO ACTION ON UPDATE RESTRICT;