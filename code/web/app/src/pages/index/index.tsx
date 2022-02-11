import * as React from 'react';
import {View,Button} from 'remax/one';
import styles from './index.css';

export default () => {
    return (
        <View className={styles.app}>
            <View className={styles.header}/>
            <Button onTap={()=>console.log("hello")}>确认</Button>
        </View>
    );
};
