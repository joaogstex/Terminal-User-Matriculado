package br.com.ecoground.user.user_ecoground.terminal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "br.com.ecoground.user.user_ecoground.terminal.repository",
    entityManagerFactoryRef = "terminalEntityManagerFactory",
    transactionManagerRef = "terminalTransactionManager"
)
@EntityScan(basePackages = "br.com.ecoground.user.user_ecoground.terminal.entity")
public class TerminalDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "terminal.datasource")
    public DataSource terminalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean terminalEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("terminalDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("br.com.ecoground.user.user_ecoground.terminal.entity")
                .persistenceUnit("terminal")
                .build();
    }

    @Bean
    public PlatformTransactionManager terminalTransactionManager(
            @Qualifier("terminalEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}