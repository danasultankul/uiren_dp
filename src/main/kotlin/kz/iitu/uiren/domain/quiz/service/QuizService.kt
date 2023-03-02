package kz.iitu.uiren.domain.quiz.service

import kz.iitu.uiren.domain.quiz.model.Quiz
import kz.iitu.uiren.domain.quiz.model.QuizType
import kz.iitu.uiren.domain.quiz.repo.QuizRepo
import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.domain.user.repo.UserRepo
import kz.iitu.uiren.ui.dto.quiz.request.AddQuizRequest
import kz.iitu.uiren.ui.dto.quiz.request.UpdateQuizRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class QuizService : IQuizService {

    @Autowired
    private lateinit var quizRepo: QuizRepo

    @Autowired
    private lateinit var userRepo: UserRepo

    override fun addLessonQuiz(addQuizRequest: AddQuizRequest): ResponseEntity<Any> {
        val quizOptional = quizRepo.findByForIdAndType(addQuizRequest.forId, QuizType.LESSON.name)
        if (quizOptional.isPresent) {
            quizRepo.delete(quizOptional.get())
        }
        quizRepo.save(
            Quiz(
                name = addQuizRequest.name,
                description = addQuizRequest.description,
                type = QuizType.LESSON.name,
                forId = addQuizRequest.forId
            )
        )
        return ResponseEntity.ok("Saved")
    }

    override fun addCourseQuiz(addQuizRequest: AddQuizRequest): ResponseEntity<Any> {
        val quizOptional = quizRepo.findByForIdAndType(addQuizRequest.forId, QuizType.COURSE.name)
        if (quizOptional.isPresent) {
            quizRepo.delete(quizOptional.get())
        }
        quizRepo.save(
            Quiz(
                name = addQuizRequest.name,
                description = addQuizRequest.description,
                type = QuizType.COURSE.name,
                forId = addQuizRequest.forId
            )
        )
        return ResponseEntity.ok("Saved")
    }

    override fun updateQuiz(updateQuizRequest: UpdateQuizRequest): ResponseEntity<Any> {
        val quizOptional = quizRepo.findById(updateQuizRequest.id)
        if (!quizOptional.isPresent) {
            return ResponseEntity.badRequest().body("Quiz not found")
        }
        val quiz = quizOptional.get()
        quiz.name = updateQuizRequest.name
        quiz.description = updateQuizRequest.description
        quizRepo.save(quiz)
        return ResponseEntity.ok("Updated")
    }

    override fun deleteQuiz(quizId: Long): ResponseEntity<Any> {
        val quizOptional = quizRepo.findById(quizId)
        if (!quizOptional.isPresent) {
            return ResponseEntity.badRequest().body("Quiz not found")
        }
        quizRepo.delete(quizOptional.get())
        return ResponseEntity.ok("Deleted")
    }

    override fun getLessonQuiz(lessonId: Long): ResponseEntity<Any> {
        val quizOptional = quizRepo.findByForIdAndType(lessonId, QuizType.LESSON.name)
        if (!quizOptional.isPresent) {
            return ResponseEntity.badRequest().body("Quiz not found")
        }
        return ResponseEntity.ok(quizOptional.get())
    }

    override fun getCourseQuiz(courseId: Long): ResponseEntity<Any> {
        val quizOptional = quizRepo.findByForIdAndType(courseId, QuizType.LESSON.name)
        if (!quizOptional.isPresent) {
            return ResponseEntity.badRequest().body("Quiz not found")
        }
        return ResponseEntity.ok(quizOptional.get())
    }

    override fun saveQuiz(user: User, quizId: Long): ResponseEntity<Any> {
        user.quizId = quizId
        userRepo.save(user)
        return ResponseEntity.ok("Saved")
    }

}