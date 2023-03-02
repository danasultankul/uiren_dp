package kz.iitu.uiren.domain.question.repo

import kz.iitu.uiren.domain.question.model.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepo : CrudRepository<Question, Long> {

    fun findAllByQuizId(quizId: Long): MutableList<Question>

}