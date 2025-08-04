package ru.bs.cbdc.vaadin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.bs.cbdc.vaadin.entity.Contact


interface ContactRepository : JpaRepository<Contact?, Long?> {
    @Query(
        ("select c from Contact c " +
                "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
                "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    )
    fun search(@Param("searchTerm") searchTerm: String?): List<Contact?>?
}