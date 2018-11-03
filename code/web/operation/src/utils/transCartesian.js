/**
 * 转换数据成符合热力图的数据格式
 * @params list 时间集合(格式为:]{day: '2018-06-12', time: '15:00'}])
 */
function transCartesian(list) {
  list.sort(function (a, b) {
    let value1 = new Date(a.day).getDay(),
      value2 = new Date(b.day).getDay();
    let time1 = new Date(a.day + ' ' + a.time).getHours(),
      time2 = new Date(b.day + ' ' + b.time).getHours()
    if (value1 === value2) {
      return time1 - time2;
    }
    return value1 - value2;
  });
  let temps = []
  list.map((item) => {
      let week = new Date(item.day).getDay()
      switch (week) {
        case 0 :
          temps.push({day: '周日', time: item.time})
          break
        case 1 :
          temps.push({day: '周一', time: item.time})
          break
        case 2 :
          temps.push({day: '周二', time: item.time})
          break
        case 3 :
          temps.push({day: '周三', time: item.time})
          break
        case 4 :
          temps.push({day: '周四', time: item.time})
          break
        case 5 :
          temps.push({day: '周五', time: item.time})
          break
        case 6 :
          temps.push({day: '周六', time: item.time})
          break
      }
    }
  )
  let result = []
  for (let i = 0; i < temps.length;) {
    let count = 0
    for (let j = i; j < temps.length; j++) {
      if (temps[i].day === temps[j].day && temps[i].time === temps[j].time) {
        count++
      }
    }
    result.push([temps[i].time, temps[i].day, count])
    i += count
  }
  return result
}

