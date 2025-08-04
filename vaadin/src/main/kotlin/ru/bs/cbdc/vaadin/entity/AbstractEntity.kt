package ru.bs.cbdc.vaadin.entity

import jakarta.persistence.*

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    var id: Long? = null

    @Version
    val version: Int = 0
}