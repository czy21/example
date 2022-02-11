import React from 'react'
import {View, Text} from '@tarojs/components'
import '@v/home/index.scss'

const Index: React.FC<any> = (props: any) => {
  return (
    <View className='components-page'>
      <View className='flex-wrp' style='flex-direction:column;'>
        <View className='flex-item flex-item-V demo-text-1'>
          <Text>A</Text>
        </View>
        <View className='flex-item flex-item-V demo-text-2'>
          <Text>B</Text>
        </View>
        <View className='flex-item flex-item-V demo-text-3'>
          <Text>C</Text>
        </View>
      </View>
    </View>
  )
}
export default Index
