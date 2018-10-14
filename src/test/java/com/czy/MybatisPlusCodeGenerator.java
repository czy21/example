package com.czy;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D://");//设置输出路径
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("陈昭宇");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("sasa");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/czy_oa?characterEncoding=utf8&useSSL=false");
        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("oa_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setSuperEntityClass("com.czy.core.universal.BaseEntity");//设置实体基类
        strategy.setSuperEntityColumns("Id","AddedTime","ModifiedTime");
        strategy.setSuperMapperClass("com.czy.core.universal.BaseDao");
        strategy.setSuperServiceClass("com.czy.core.universal.BaseService");
        strategy.setSuperServiceImplClass("com.czy.core.universal.BaseServiceImpl");
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        //自定义模板配置
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        tc.setXml("/templates/mapper.xml.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        mpg.setTemplate(tc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.czy");
        pc.setMapper("dao");
        pc.setEntity("entity.po");
        pc.setXml("xml");

        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}
