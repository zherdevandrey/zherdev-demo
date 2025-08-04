package ru.bs.cbdc.vaadin.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bs.cbdc.vaadin.entity.Company

interface CompanyRepository : JpaRepository<Company, Long>