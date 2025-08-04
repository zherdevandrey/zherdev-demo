package ru.bs.cbdc.concurrent.jpa.repository

import jakarta.persistence.LockModeType
import jakarta.persistence.QueryHint
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.QueryHints
import ru.bs.cbdc.concurrent.jpa.entity.AbstractEntity

interface ConcurrentRepository<E : AbstractEntity> : JpaRepository<E, Long> {

    @QueryHints(QueryHint(name = "javax.persistence.lock.timeout", value = "-2"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findAll(spec: Specification<E>, pageable: Pageable): Page<E>
}
