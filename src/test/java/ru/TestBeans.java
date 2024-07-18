package ru;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.*;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.sql.ast.SqlAstTranslatorFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
@ContextConfiguration(initializers = TestBeans.DockerPostgreDataSourceInitializer.class)
public class TestBeans {
    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>
            ("postgres:15");





    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer(DynamicPropertyRegistry registry) {

        System.out.println("================================================"+postgreDBContainer.getPassword());
        System.out.println("================================================"+postgreDBContainer.getPassword());

        return postgreDBContainer;//new PostgreSQLContainer<>("postgres:15");
    }




    public static class DockerPostgreDataSourceInitializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            System.out.println("======================++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword()
            );
        }
    }





}
