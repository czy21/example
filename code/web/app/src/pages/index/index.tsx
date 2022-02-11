import * as React from 'react';
import {View, Button, Text} from 'remax/one';
import styles from './index.css';

export default () => {
    return (
        <View className={styles.app}>
            <Text>你好我的世界</Text>
            <Button onTap={() => console.log("hello")}>确认</Button>
        </View>
    );
};
