# !/usr/bin/env python
import configparser
import os
import re
from subprocess import Popen, PIPE

from default import font_color
from default.font_color import printWithColor
from default.path_default import db_version_path
from local.local_build_db import built_version_sql

config_path = os.path.abspath("../conf")

cf = configparser.ConfigParser()
cf.read(config_path + "\\mysql.conf")
cf.read(config_path + "\\mongo.conf")
cf.read(config_path + "\\shell.conf")
cf.read(config_path + "\\remote.conf")
erp_home = cf.get("home", "erp_home")
api_build_cmd = cf.get("api", "build_shell")
api_update_cmd = cf.get("api", "update_version")
api_commit_cmd = cf.get("api", "commit_version")
api_revert_cmd = cf.get("api", "revert_version")
web_build_cmd = cf.get("web", "build_shell")


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


class mysql:
    def __init__(self, t_host="", t_port="", t_user="", t_pwd="", t_user_param="", t_db=""):
        self._t_host = t_host
        self._t_port = t_port
        self._t_user = t_user
        self._t_db = t_db
        self._t_pwd = t_pwd
        self.t_user_param = t_user_param if (t_user_param != "") else " --host=" + self._t_host + " --port=" + self._t_port + " --user=" + self._t_user + " --password=" + self._t_pwd

    def obtain_exec_cmd(self):
        return "mysql " + "--database=" + self._t_db + self.t_user_param

    def obtain_dump_cmd(self):
        return "mysqldump " + self._t_db + self.t_user_param

    # 更新数据库版本号
    def update_release_config(self, version_value, execute=True):
        version_sql = "\"update release_config set config_value=\'" + version_value + "\' where config_key='Version';"
        build_date_sql = "update release_config set config_value=Now() where config_key='BuildDate'\""
        cmd = self.obtain_exec_cmd() + " -e " + version_sql + build_date_sql
        if execute:
            printWithColor('updating ' + self._t_db + ' release config', font_color.GREEN)
            printWithColor(cmd, font_color.DARKSKYBLUE)
            os.system(cmd)
            printWithColor('updated ' + self._t_db + ' release config', font_color.GREEN)
        return cmd

    # 查询数据库版本号
    def select_release_config(self, execute=True):
        cmd = self.obtain_exec_cmd() + " -Ne" + " \"select config_value from release_config where config_key='Version'\""
        if execute:
            return re.findall("\d+", str(Popen(cmd, stdout=PIPE, stderr=PIPE).stdout.readline()))[0]
        return cmd

    # 重新创建数据库
    def create_db(self, execute=True):
        cmd = 'mysql' + self.t_user_param + " -e " + "\"drop database if exists " + self._t_db + "; create database if not exists " + self._t_db + " default charset utf8 collate utf8_general_ci;\""
        if execute:
            os.system(cmd)
        return cmd

    # 恢复源数据库至目标数据库
    def restore_db(self, s_dump_cmd, execute=True):
        cmd = self.create_db(False) + " && " + s_dump_cmd + " | " + self.obtain_exec_cmd()
        if execute:
            printWithColor('restoring ' + self._t_db, font_color.GREEN)
            printWithColor(cmd, font_color.DARKSKYBLUE)
            os.system(cmd)
            printWithColor('restored ' + self._t_db, font_color.GREEN)
        return cmd

    # 升级数据库,并更新版本
    def upgrade_db(self, exec_file_path, s_version_val, t_version_val=""):
        phy_max_db_dir = max(os.listdir(db_version_path))
        if t_version_val != "" and phy_max_db_dir[: -1] > s_version_val:
            printWithColor('upgrading ' + self._t_db, font_color.GREEN)
            cmd = self.obtain_exec_cmd() + mysql_import_sql_file(built_version_sql(t_version_val + "v"), os.path.basename(exec_file_path))
            printWithColor(cmd, font_color.DARKSKYBLUE)
            os.system(cmd)
            printWithColor('upgraded ' + self._t_db, font_color.GREEN)
            self.update_release_config(t_version_val)
        elif t_version_val != "":
            self.update_release_config(t_version_val)
        elif phy_max_db_dir[: -1] > s_version_val:
            printWithColor('upgrading ' + self._t_db, font_color.GREEN)
            cmd = self.obtain_exec_cmd() + mysql_import_sql_file(built_version_sql(phy_max_db_dir), os.path.basename(exec_file_path))
            printWithColor(cmd, font_color.DARKSKYBLUE)
            os.system(cmd)
            printWithColor('upgraded ' + self._t_db, font_color.GREEN)
            self.update_release_config(phy_max_db_dir[: -1])


# mysql备份压缩数据库
def mysqldump_contraction_db(t_db, t_user_param, bak_path):
    return "mysqldump --databases " + t_db + t_user_param + " --routines --single-transaction | gzip > " + bak_path + "\\mysql.gz"


# 导入sql文件
def mysql_import_sql_file(sql_file_name, log_file_name):
    return " -vvv < " + sql_file_name + " > " + log_file_name + ".log"
