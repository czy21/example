package com.team.entity;

import com.baidu.fsg.uid.UidGenerator;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;

import javax.annotation.Resource;


public class PrimaryKeyGenerator implements IKeyGenerator {

    @Resource
    private UidGenerator uidGenerator;

    @Override
    public String executeSql(String s) {
        return String.valueOf(uidGenerator.getUID());
    }
}
