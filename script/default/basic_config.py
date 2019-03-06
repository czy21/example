# !/usr/bin/env python
import configparser
import os

config_path = os.path.abspath("../conf")

cf = configparser.ConfigParser()
cf.read(config_path + "\\mysql.conf")
cf.read(config_path + "\\mongo.conf")
cf.read(config_path + "\\shell.conf")
cf.read(config_path + "\\remote.conf")
erp_home = cf.get("home", "erp_home")
api_build_shell = cf.get("api", "build_shell")
api_update_shell = cf.get("api", "update_version")
api_commit_shell = cf.get("api", "commit_version")
api_revert_shell = cf.get("api", "revert_version")
web_build_shell = cf.get("web", "build_shell")


class ssh_config:
    def __init__(self, key):
        self.host_name = cf.get(key, "host_name")
        self.user_name = cf.get(key, "user_name")


class mysql_db_config:
    def __init__(self, key):
        self.db_host = cf.get(key, "db_host")
        self.db_port = cf.get(key, "db_port")
        self.db_user = cf.get(key, "db_user")
        self.db_pass = cf.get(key, "db_pass")
        self.db_name = cf.get(key, "db_name")
        self.db_bak_name = cf.get(key, "db_bak_name")


class mongo_db_config:
    def __init__(self, key):
        self.db_host = cf.get(key, "db_host")
        self.db_port = cf.get(key, "db_port")
        self.db_name = cf.get(key, "db_name")
        self.db_bak_name = cf.get(key, "db_bak_name")


def mysql_cmd(db_name, user_param):
    return "mysql " + db_name + user_param


def mysqldump_cmd(db_name, user_param):
    return "mysqldump " + db_name + user_param


# 更新数据库版本号
def mysql_update_release_config(db_name, user_param, version_value):
    return mysql_cmd(db_name, user_param) + " -Ne" \
           + " \"update release_config set config_value=\'" + version_value + "\' where config_key='Version';" \
           + "update release_config set config_value=Now() where config_key='BuildDate'\""


# 查询数据库版本号
def mysql_select_release_config(db_name, user_param):
    return mysql_cmd(db_name, user_param) + " -Ne" + " \"select config_value from release_config where config_key='Version'\""


# mysql连接的用户信息
def mysql_user_param(port, user, pwd):
    return " -P" + port + " -u" + user + " -p" + pwd + " "


# 恢复源数据库至目标数据库
def mysql_restore_db(s_db, s_user_param, t_db, t_user_param):
    return mysql_create_db(t_user_param, t_db) + " && " + mysqldump_cmd(s_db, s_user_param) + " | " + mysql_cmd(t_db, t_user_param)


# 重新创建数据库
def mysql_create_db(t_user_param, t_db):
    return 'mysql' + t_user_param + "-e " + "\"drop database if exists " + t_db + "; create database if not exists " + t_db + " default charset utf8 collate utf8_general_ci;\""


# 导入sql文件
def mysql_import_sql_file(sql_file_name, file):
    return "-vvv < " + sql_file_name + " > " + file + ".log"
