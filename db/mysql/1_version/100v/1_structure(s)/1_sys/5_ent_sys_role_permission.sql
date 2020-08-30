CREATE TABLE `ent_sys_role_permission`  (
  `id` varchar(36)  NOT NULL,
  `role_id` varchar(36)  NOT NULL,
  `permission_key` varchar(100)  NOT NULL,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`) ,
  INDEX `fk_RolePermission_Permission`(`permission_key`) ,
  INDEX `fk_RolePermission_Role`(`role_id`) ,
  CONSTRAINT `fk_RolePermission_Permission` 
  FOREIGN KEY (`permission_key`) REFERENCES `met_sys_permission` (`key`) 
  ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_RolePermission_Role` 
  FOREIGN KEY (`role_id`) REFERENCES `ent_sys_role` (`id`) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='角色权限表';