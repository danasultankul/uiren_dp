package kz.iitu.uiren.domain.answer.repo

import kz.iitu.uiren.domain.answer.model.TestResult
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TestResultRepo : CrudRepository<TestResult, Long> {

    fun findAllByUserId(userId: Long): MutableList<TestResult>

}