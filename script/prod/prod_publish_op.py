# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import ssh_config
from default.temp_path import temp_operation_path

remote = ssh_config("ssh")
ssh_user = remote.user_name + "@" + remote.host_name


def run():
    os.system('ssh ' + ssh_user + ' "rm -rf web/operation/*"')
    os.system("scp -r " + temp_operation_path + " " + ssh_user + ":web")


if __name__ == '__main__':
    run()
    os.system("pause")
