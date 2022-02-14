import React from 'react'
import {View, Text, Swiper, SwiperItem} from '@tarojs/components'
import '@v/home/index.scss'

const Index: React.FC<any> = (props: any) => {
  return (
    <View className='flex-wrp' style='flex-direction:column;'>
      <View className='flex-item flex-item-V'>
        <Text>A</Text>
      </View>
      <View className='flex-item flex-item-V'>
        <Text>B</Text>
      </View>
      <View className='flex-item flex-item-V'>
        <Text>C</Text>
      </View>
    </View>
  )
}
export default Index
