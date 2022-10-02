package com.github.dghg.myapp.api.outbound.jpa.repository

import com.github.dghg.myapp.api.outbound.jpa.User
import org.springframework.data.repository.Repository
import java.util.stream.Stream

interface UserRepository: Repository<User, Long> {
    fun findAll(): Stream<User>
}