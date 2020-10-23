import React from "react";
import {Button} from "antd";
import List from "@c/List";


export default class A extends React.Component<any, any> {
    handleClick = () => {
        console.log('this is:', this);
    }

    render() {
        return (
            <div>
                <Button onClick={this.handleClick}>确定</Button>
                <List>ss</List>
            </div>
        );
    }
}