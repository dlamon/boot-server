package com.example.boot.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages="com.example.boot.server.dao.test", sqlSessionTemplateRef="testSqlSessionTemplate" )
public class TestDataSourceConfig {

    @Bean("testDruidDataSource")
    @ConfigurationProperties(prefix="spring.datasource.test.druid")
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("testSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("testDruidDataSource") DruidDataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean("testDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("testDruidDataSource") DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("testSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("testSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}