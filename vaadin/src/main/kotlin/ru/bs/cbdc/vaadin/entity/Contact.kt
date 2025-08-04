package ru.bs.cbdc.vaadin.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
class Contact : AbstractEntity() {
    var firstName: String = ""

    var lastName: String = ""

    var email: String = ""

    @ManyToOne
    @JoinColumn(name = "company_id")
    @NotNull
    @JsonIgnoreProperties("employees")
    var company: Company? = null

    @NotNull
    @ManyToOne
    var status: Status? = null

}