package ru.bs.cbdc.vaadin.entity

import jakarta.annotation.Nullable
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.Formula
import java.util.*


@Entity
class Company : AbstractEntity() {
    var name: @NotBlank String? = null

    @OneToMany(mappedBy = "company")
    @Nullable
    var employees: List<Contact> = LinkedList<Contact>()

    @Formula("(select count(c.id) from Contact c where c.company_id = id)")
    val employeeCount: Int = 0
}