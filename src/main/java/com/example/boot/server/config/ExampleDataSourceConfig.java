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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiaoWei
 */
@Configuration
@MapperScan(basePackages="com.example.boot.server.dao.example", sqlSessionTemplateRef="exampleSqlSessionTemplate" )
public class ExampleDataSourceConfig {

    @Bean("exampleDruidDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.example.druid")
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("exampleSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("exampleDruidDataSource") DruidDataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean("exampleDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("exampleDruidDataSource") DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("exampleSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("exampleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}