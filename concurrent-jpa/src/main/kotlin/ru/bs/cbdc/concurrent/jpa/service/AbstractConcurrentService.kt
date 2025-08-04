package ru.bs.cbdc.concurrent.jpa.service

import jakarta.persistence.criteria.Predicate
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import ru.bs.cbdc.concurrent.jpa.entity.AbstractEntity
import ru.bs.cbdc.concurrent.jpa.repository.ConcurrentRepository
import java.util.function.BiConsumer
import java.util.function.BiFunction
import java.util.function.Consumer


abstract class AbstractConcurrentService<E : AbstractEntity>(private val concurrentRepository: ConcurrentRepository<E>) {

    abstract fun getFetchSize():Int

    @Transactional
    open fun run(fieldName: String, fieldValue: Any?, consumer: Consumer<E>): MutableList<E> {
        val entities = concurrentRepository.findAll(
            buildSpec(fieldName, fieldValue),
            PageRequest.of(0, getFetchSize())
        )
        entities.forEach { consumer.accept(it) }
        return concurrentRepository.saveAll(entities.content)
    }

    private fun buildSpec(fieldName: String, fieldValue: Any?): Specification<E> {
        return Specification { root, _, cb ->
            val predicates = mutableListOf<Predicate>()
            predicates.add(cb.equal(root.get<String>(fieldName), fieldValue))
            cb.and(*predicates.toTypedArray())
        }
    }
}