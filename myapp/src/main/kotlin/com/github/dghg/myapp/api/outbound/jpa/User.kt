package com.github.dghg.myapp.api.outbound.jpa

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "name")
    val name: String = "",

    @Column(name = "name2")
    val name2: String = "",
)