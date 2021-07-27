import datetime
import json
import threading
import time
from concurrent.futures import ThreadPoolExecutor

import requests

api_host = "http://127.0.0.1:8080"


def user_detail(x):
    r = requests.post(url="{}/user/detail".format(api_host),
                      data=json.dumps({
                          "id": "2f5aa878-352d-49a9-a18e-188449e9e649"
                      }),
                      headers={
                          'Content-Type': 'application/json'
                      })
    current_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    current_timestamp = str(time.time())
    current_user = str(x + 1)
    response_data = r.json()
    map = {
        "current_user": current_user,
        "current_time": current_time,
        "response_data": response_data,
        "current_timestamp": current_timestamp
    }
    return map


def stock_reduce(x):
    user_id = str(x + 1)
    start_time = datetime.datetime.now()
    start_timestamp = int(start_time.timestamp() * 1000000)
    print(start_timestamp)
    r = requests.post(url="{}/stock/sale".format(api_host),
                      data=json.dumps({
                          "id": "2f5aa878-352d-49a9-a18e-188449e9e649",
                          "userId": user_id,
                          "submitDate": start_timestamp
                      }),
                      headers={
                          'Content-Type': 'application/json'
                      })
    end_time = datetime.datetime.now()
    request_time = str((end_time - start_time).microseconds / 1000) + "ms"
    response_data = r.json()
    map = {
        "userId": user_id,
        "response_data": response_data,
        "start_time": start_time.strftime('%Y-%m-%d %H:%M:%S %f'),
        "end_time": end_time.strftime('%Y-%m-%d %H:%M:%S %f'),
        "request_time": request_time,
    }
    return map


if __name__ == '__main__':
    print('MainThread %s is running...' % threading.current_thread().name)
    result = []
    with ThreadPoolExecutor(16) as executor:
        for data in executor.map(stock_reduce, range(1)):
            result.append(data)
    p = list(sorted(result, key=lambda x: x["start_time"]))
    for t in p:
        print(t)
    print('MainThread %s ended.' % threading.current_thread().name)
