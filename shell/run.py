#!/usr/bin/env python3
import importlib
import sys
from pathlib import Path

from script.utility import log as log_util

logger = log_util.Logger(__name__)


def exec_file(source_dict: {}):
    action_env = Path(sys.argv[0]).cwd().stem
    env_module = importlib.import_module("".join(["shell.", action_env, "._env"]))
    source_mod_files = [{"module": importlib.import_module(m), "func": f} for m, f in source_dict.items()]
    default_common_mod = importlib.import_module("script.domain.default.common")
    common_param = getattr(default_common_mod, "get_params")()
    logger.info(common_param)
    [getattr(i["module"], j)() for i in source_mod_files for j in i["func"]]