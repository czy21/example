var ary = [
  {day: "周二", time: "15:00"},
  {day: "周三", time: "15:00"},
  {day: "周二", time: "15:00"},
  {day: "周三", time: "15:00"},
  {day: "周三", time: "17:00"},
  {day: "周二", time: "15:00"},
  {day: "周三", time: "15:00"},
  {day: "周三", time: "16:00"},
  {day: "周三", time: "16:00"},
  {day: "周四", time: "11:00"},
  {day: "周三", time: "16:00"},
  {day: "周二", time: "16:00"},
  {day: "周四", time: "16:00"},
  {day: "周二", time: "16:00"},

];
var compare = function (obj1, obj2) {
  var val1 = obj1.day;
  var val2 = obj2.day;
  if (val1 > val2) {
    return -1;
  } else if (val1 < val2) {
    return 1;
  } else {
    return 0;
  }
}
var res = [];
ary.sort(compare);
console.log(ary)
// tempAry = []
// for (var i = 0; i < ary.length;) {
//   for (var j = i; j < ary.length; j++) {
//     if (ary[i].day === ary[j].day) {
//       tempAry.push(ary[i])
//     }
//   }
// }
// console.log(ary)
// console.log(tempAry)

// for (var i = 0; i < ary.length;) {
//
//   var count = 0;
//   for (var j = i; j < ary.length; j++) {
//
//     if (ary[i].day === ary[j].day && ary[i].time === ary[j].time) {
//       count++;
//     }
//   }
//   res.push([ary[i].day, ary[i].time, count]);
//   i += count;
// }
// console.log(res)
