package com.example.boot.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author LiaoWei
 */
@Configuration
@MapperScan(basePackages="com.example.boot.server.dao.client", sqlSessionTemplateRef="clientSqlSessionTemplate" )
class ClientDataSourceConfig {
    @Bean("clientDruidDataSource")
    @ConfigurationProperties(prefix="spring.datasource.client.druid")
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("clientSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("clientDruidDataSource") DruidDataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:dao/client/**/*.xml"));
        return bean.getObject();
    }

    @Bean("clientDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("clientDruidDataSource") DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("clientSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("clientSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
