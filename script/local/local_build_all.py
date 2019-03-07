# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from local import local_build_api, local_build_op


def to_temp():
    local_build_api.to_temp()
    local_build_op.to_temp()


if __name__ == '__main__':
    to_temp()
    os.system("pause")
