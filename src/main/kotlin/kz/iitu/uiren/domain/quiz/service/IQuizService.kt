package kz.iitu.uiren.domain.quiz.service

import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.ui.dto.quiz.request.AddQuizRequest
import kz.iitu.uiren.ui.dto.quiz.request.UpdateQuizRequest
import org.springframework.http.ResponseEntity

interface IQuizService {

    fun addLessonQuiz(addQuizRequest: AddQuizRequest): ResponseEntity<Any>

    fun addCourseQuiz(addQuizRequest: AddQuizRequest): ResponseEntity<Any>

    fun updateQuiz(updateQuizRequest: UpdateQuizRequest): ResponseEntity<Any>

    fun deleteQuiz(quizId: Long): ResponseEntity<Any>

    fun getLessonQuiz(lessonId: Long): ResponseEntity<Any>

    fun getCourseQuiz(courseId: Long): ResponseEntity<Any>

    fun saveQuiz(user: User, quizId: Long): ResponseEntity<Any>

}