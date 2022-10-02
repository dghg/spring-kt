package com.github.dghg.myapp.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.github.dghg.myapp"]
)
class DataSoureConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.main")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .build()
    }

    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource())
            .packages("com.github.dghg.myapp")
            .persistenceUnit("entityManager")
            .build()
    }

    @Bean
    fun transactionManager(
        entityManagerFactory: EntityManagerFactory,
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}