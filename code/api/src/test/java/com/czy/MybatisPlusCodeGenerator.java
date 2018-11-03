package com.czy;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D://");
        gc.setFileOverride(true);
        gc.setAuthor("陈昭宇");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("sasa");
        dsc.setUrl("jdbc:mysql://localhost:3306/erp?&serverTimezone=UTC&characterEncoding=utf-8&useSSL=false&useUnicode=true");
        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("sys_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.team.core.universal.BaseEntity");
        strategy.setSuperEntityColumns("AddedTime", "ModifiedTime", "AddedUser", "ModifiedUser");
        strategy.setSuperMapperClass("com.team.core.universal.BaseEntity.BaseDao");
        strategy.setSuperServiceClass("com.team.core.universal.BaseEntity.BaseService");
        strategy.setSuperServiceImplClass("com.team.core.universal.BaseEntity.BaseServiceImpl");
        mpg.setStrategy(strategy);

        //自定义模板配置
        TemplateConfig tc = new TemplateConfig();

        tc.setController("/templates/controller.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/dao.java.vm");
        tc.setXml("/templates/mapper.xml.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        mpg.setTemplate(tc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.team");
        pc.setMapper("dao");
        pc.setEntity("entity.po");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}
