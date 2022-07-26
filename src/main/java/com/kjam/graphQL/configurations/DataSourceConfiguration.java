package com.kjam.graphQL.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class DataSourceConfiguration extends AbstractR2dbcConfiguration {

    @Override
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
            .url("mem:testdb;DB_CLOSE_DELAY=-1;")
            .username("sa")
            .build()
        );
    }
}
