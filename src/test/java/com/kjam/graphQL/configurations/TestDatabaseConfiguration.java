package com.kjam.graphQL.configurations;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.connection.ConnectionFactoryUtils;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@TestConfiguration
public class TestDatabaseConfiguration {

    /**
     * There is an issue with r2dbc where the Local H2 HD doesn't 
     * shut down the database when the application context is closed.
     * 
     * More info here.
     * https://github.com/spring-projects/spring-boot/issues/28345
     * 
     * Spring is working on a fix. Until then here is a work around.
     * @param connectionFactory
     * @return
     */
    @Bean
    public DisposableBean embeddedDatabaseShutdownExecutor(ConnectionFactory connectionFactory) {
        return () -> {
            ConnectionFactoryUtils.getConnection(connectionFactory)
                .flatMap((connection) -> Mono.from(connection.createStatement("SHUTDOWN").execute()))
                .block();
        };
    }
    
}
