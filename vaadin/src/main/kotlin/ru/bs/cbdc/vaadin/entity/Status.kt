package ru.bs.cbdc.vaadin.entity

import jakarta.persistence.Entity


@Entity
class Status : AbstractEntity() {
    var name: String? = null
}