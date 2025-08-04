package ru.bs.cbdc.concurrent.jpa.service

import org.springframework.stereotype.Component
import ru.bs.cbdc.concurrent.jpa.repository.TestConcurrentRepository
import ru.bs.cbdc.concurrent.jpa.entity.ConcurrentEntity

@Component
class ConcurrentService(testConcurrentRepository: TestConcurrentRepository): AbstractConcurrentService<ConcurrentEntity>(testConcurrentRepository) {
    override fun getFetchSize(): Int  = 1
}