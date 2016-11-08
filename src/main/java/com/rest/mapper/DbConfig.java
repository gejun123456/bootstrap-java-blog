package com.rest.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@Configuration
public class DbConfig {
    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource createDateSource(){

        File file = new File("/env/databaseconfig");
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(prop.getProperty("url"));
        druidDataSource.setUsername(prop.getProperty("username"));
        druidDataSource.setPassword(prop.getProperty("password"));
        druidDataSource.setInitialSize(3);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMaxWait(6000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(6000);
        druidDataSource.setMinEvictableIdleTimeMillis(30000);
        druidDataSource.setValidationQuery("SELECT 1 from dual");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(true);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }
}
