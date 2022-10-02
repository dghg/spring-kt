package com.github.dghg.myapp

import com.github.dghg.myapp.api.outbound.jpa.User
import com.github.dghg.myapp.api.outbound.jpa.repository.UserRepository
import com.github.dghg.myapp.config.DataSoureConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional
import java.lang.Thread.sleep
import javax.persistence.*

@DataJpaTest(
    excludeAutoConfiguration = [
        DataSourceAutoConfiguration::class,
    ]
)
@Import(DataSoureConfig::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringDataJpaStream {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `init data & iterate`() {
        (1..1000)
            .forEach {
                val user = User(name = "test", name2 = "test2")
                em.persist(user)
            }

        userRepository
            .findAll()
            .limit(100)
            .forEach {
                with(it) {
                    println ("$id $name $name2")
                    em.detach(this)
                }
            }
        // heap dump
        sleep(10000000L)
    }
}