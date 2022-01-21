import React from "react";
import { View, Text } from '@tarojs/components'
import './index.less'

// export default class Index extends Component {
//
//   componentWillMount () { }
//
//   componentDidMount () {
//     console.log("world")
//   }
//
//   componentWillUnmount () { }
//
//   componentDidShow () { }
//
//   componentDidHide () { }
//
//   render () {
//     return (
//       <View className='index'>
//         <Text>Hello world!</Text>
//       </View>
//     )
//   }
// }
const List: React.FC<any> = () => {

  return(
      <View className='index'>
        <Text>我的世界，黑暗之中的光芒</Text>
      </View>
  )
}
export default List
