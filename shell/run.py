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
    for t in source_mod_files:
        source_mod_file = t["module"].__file__
        with open(source_mod_file, "rb") as f:
            source_code = f.read()
        exec_code = compile(source_code, source_mod_file, "exec")
        scope = {}
        exec(exec_code, scope)
        for f in t["func"]:
            scope.get(f, None)()
