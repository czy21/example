# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import ssh_config
from default.temp_path import temp_api_path
from local import local_build_api

remote = ssh_config("ssh")
ssh_user = remote.user_name + "@" + remote.host_name


def run():
    os.system('ssh ' + ssh_user + ' "api/api-restart.sh -d"')
    os.system("scp -r " + temp_api_path + " " + ssh_user + ":")
    os.system('ssh ' + ssh_user + ' "api/api-restart.sh -u"')


if __name__ == '__main__':
    local_build_api.build_to_temp()
    run()
    os.system("pause")
