package kz.iitu.uiren.domain.answer.repo

import kz.iitu.uiren.domain.answer.model.Answer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepo : CrudRepository<Answer, Long> {

    fun findAllByQuestionId(questionId: Long): MutableList<Answer>

}