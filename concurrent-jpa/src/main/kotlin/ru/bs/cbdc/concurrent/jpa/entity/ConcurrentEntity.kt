package ru.bs.cbdc.concurrent.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ConcurrentEntity() : AbstractEntity() {

    @Column(name ="status")
    var status: String? = null

}