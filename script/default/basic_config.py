# !/usr/bin/env python
import os
import configparser

config_path = os.path.abspath("../conf")

cf = configparser.ConfigParser()
cf.read(config_path + "\\mysql.conf")
cf.read(config_path + "\\shell.conf")
cf.read(config_path + "\\remote.conf")
erp_home = cf.get("home", "erp_home")
api_build_shell = cf.get("api", "build_shell")
api_update_shell = cf.get("api", "update_version")
api_commit_shell = cf.get("api", "commit_version")
api_revert_shell = cf.get("api", "revert_version")
web_build_shell = cf.get("web", "build_shell")


class db_config:
    def __init__(self, key):
        self.db_host = cf.get(key, "db_host")
        self.db_port = cf.get(key, "db_port")
        self.db_user = cf.get(key, "db_user")
        self.db_pass = cf.get(key, "db_pass")
        self.db_name = cf.get(key, "db_name")
        self.db_bak_name = cf.get(key, "db_bak_name")


def update_release_config_sql(user_param, version_value):
    return "mysql" + user_param + " -Ne" \
           + " \"use erp;update release_config set config_value=\'" \
           + version_value + "\' where config_key='Version';" \
           + "update release_config set config_value=Now() where config_key='BuildDate'\""


def select_release_config_sql(db_name, user_param):
    return mysql_cmd(db_name,
                     user_param) + " -Ne" + " \"select config_value from release_config where config_key='Version'\""


def user_param(port, user, pwd):
    return " -P" + port + " -u" + user + " -p" + pwd + " "


def migrate_db_sql(s_db, s_user_param, t_db, t_user_param):
    return mysqldump_cmd(s_db, s_user_param) + " --add-drop-table " + "| " + mysql_cmd(t_db, t_user_param)


def mysql_cmd(db_name, user_param):
    return "mysql " + db_name + user_param


def mysqldump_cmd(db_name, user_param):
    return "mysqldump " + db_name + user_param


def import_sql_file(sql_file_name, file):
    return " < " + sql_file_name + " > " + file + ".log"
