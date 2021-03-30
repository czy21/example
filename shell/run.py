#!/usr/bin/env python3
import argparse
import importlib
import io
import json
import sys
from pathlib import Path

sys.path.append(Path(__file__).joinpath("../../").resolve().as_posix())

from script.utility import log as log_util, basic as basic_util, path as path_util

logger = log_util.Logger(__name__)


def exec_file():
    parser = argparse.ArgumentParser()
    parser.add_argument('--param', nargs="+", default=[])
    parser.add_argument('--init-output', action="store_true")
    parser.add_argument('--env', required=True)
    parser.add_argument('--log-file')
    parser.add_argument('--cmd')
    args = parser.parse_args()
    args_param_dict = dict({t.split("=")[0]: t.split("=")[1] for t in args.param})

    env_path = Path(args.env).resolve()
    env_stem = env_path.parent.stem

    log_file = log_util.parse_argv(sys.argv, "--log-file")
    if log_file != "":
        open(env_path.joinpath("../", log_file).absolute().resolve().as_posix(), 'w').close()

    # empty source log
    default_path_module = importlib.import_module("script.domain.default.path")
    getattr(default_path_module, "re_mkdir")(rm_output=args.init_output)
    print(args.init_output)

    # injected param to global
    env_pwd_mod = importlib.import_module("".join(["shell.", env_stem, "._env"]))
    if args.init_output:
        env_common_mod = env_pwd_mod.env_common
        env_common_mod.__dict__.update(dict({k: v for k, v in env_pwd_mod.__dict__.items() if k.startswith("param")}).items(), **args_param_dict)
        default_common_mod = importlib.import_module("script.domain.default.common")
        default_common_mod.__dict__.update(dict({k: v for k, v in env_common_mod.__dict__.items() if k.startswith("param")}))
        default_common_param = dict({k: v for k, v in default_common_mod.__dict__.items() if k.startswith("param")})
        output_env = path_util.pure_path_join(getattr(default_path_module, "output"), "env.py")
        with io.open(output_env, "w+", encoding="utf-8", newline="\n") as target_output:
            for k, v in default_common_param.items():
                target_output.write("=".join([k, v]))
        logger.info(basic_util.action_formatter("params", json.dumps(default_common_param, sort_keys=True)), default_common_mod.__name__)
        print("init")
    else:
        print("no init")
    if args.cmd:
        print("sff")
        # exec(args.cmd)


if __name__ == '__main__':
    exec_file()
