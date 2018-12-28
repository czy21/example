# !/usr/bin/env python
import os

os.system("python ../local/local_publish_api.py")
os.system("python ../local/local_publish_op.py")
os.system("python ./prod_publish_db.py")
