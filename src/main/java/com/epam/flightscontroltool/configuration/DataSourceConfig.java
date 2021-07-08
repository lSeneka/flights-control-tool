package com.epam.flightscontroltool.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary.hikari")
    public DataSource primaryDataSource(DataSourceProperties primaryDataSourceProperties) {
        return createDataSource(primaryDataSourceProperties);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.liquibase")
    public DataSourceProperties liquibaseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @LiquibaseDataSource
    @ConfigurationProperties("spring.datasource.liquibase.hikari")
    public DataSource liquibaseDataSource(DataSourceProperties liquibaseDataSourceProperties) {
        return createDataSource(liquibaseDataSourceProperties);
    }

    private static DataSource createDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    // TODO: hikari settings
}
