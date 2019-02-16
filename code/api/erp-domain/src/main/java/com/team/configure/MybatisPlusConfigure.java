package com.team.configure;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan({"com.team.repository.mybatis", "com.baidu.fsg.uid.worker.dao"})
public class MybatisPlusConfigure {

    private final MybatisPlusProperties properties;

    public MybatisPlusConfigure(MybatisPlusProperties properties) {
        this.properties = properties;
    }

    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        factory.setConfiguration(configuration);
        factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        factory.setMapperLocations(this.properties.resolveMapperLocations());
        GlobalConfig globalConfig = this.properties.getGlobalConfig();
        factory.setGlobalConfig(globalConfig);
        return factory.getObject();
    }
}
