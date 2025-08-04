package ru.bs.cbdc.concurrent.jpa.entity

import jakarta.persistence.*


@MappedSuperclass
abstract class AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}