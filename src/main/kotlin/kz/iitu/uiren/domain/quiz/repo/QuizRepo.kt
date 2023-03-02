package kz.iitu.uiren.domain.quiz.repo

import kz.iitu.uiren.domain.quiz.model.Quiz
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface QuizRepo : CrudRepository<Quiz, Long> {

    fun findByForIdAndType(forId: Long, type: String): Optional<Quiz>

}