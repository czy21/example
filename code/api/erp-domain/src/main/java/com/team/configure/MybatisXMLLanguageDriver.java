package com.team.configure;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;


public class MybatisXMLLanguageDriver extends XMLLanguageDriver {
    public MybatisXMLLanguageDriver() {
    }

    public MybatisDefaultParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new MybatisDefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}