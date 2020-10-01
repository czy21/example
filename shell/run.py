#!/usr/bin/env python3
import importlib
import json
import sys
from pathlib import Path

from script.utility import log as log_util

logger = log_util.Logger(__name__)


def exec_file(source_dict: {}):
    action_env = Path(sys.argv[0])
    open("".join([action_env.as_posix(), ".log"]), 'w').close()
    env_module = importlib.import_module("".join(["shell.", action_env.cwd().stem, "._env"]))
    source_mod_files = [{"module": importlib.import_module(m), "func": f} for m, f in source_dict.items()]
    default_common_mod = importlib.import_module("script.domain.default.common")
    common_param = getattr(default_common_mod, "get_params")()
    logger.info(json.dumps(common_param))
    default_path_module = importlib.import_module("script.domain.default.path")
    getattr(default_path_module, "re_mkdir")()
    [getattr(i["module"], j)() for i in source_mod_files for j in i["func"]]
