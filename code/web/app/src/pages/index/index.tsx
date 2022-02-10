import * as React from 'react';
import {View,Button,Text} from 'remax/wechat';
import styles from './index.css';

export default () => {
    return (
        <View className={styles.app}>
            <View className={styles.header}>
                <Button onClick={()=>{console.log("aaa")}}>确认</Button>
            </View>
        </View>
    );
};
