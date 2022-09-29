package com.wuqihang.syblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author Wuqihang
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {

    private final DataSource datasource;

    public MybatisConfiguration(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(datasource);
    }
}
