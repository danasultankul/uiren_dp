package kz.iitu.uiren.domain.answer.service

import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.ui.dto.answer.request.AddAnswerRequest
import kz.iitu.uiren.ui.dto.answer.request.QuestionAnswerRequest
import kz.iitu.uiren.ui.dto.answer.request.UpdateAnswerRequest
import org.springframework.http.ResponseEntity

interface IAnswerService {

    fun addAnswer(addAnswerRequest: AddAnswerRequest): ResponseEntity<Any>

    fun updateAnswer(updateAnswerRequest: UpdateAnswerRequest): ResponseEntity<Any>

    fun deleteAnswer(answerId: Long): ResponseEntity<Any>

    fun findAllAnswers(questionId: Long): ResponseEntity<Any>

    fun findAllTestResults(user: User): ResponseEntity<Any>

    fun passQuiz(user: User, answers: QuestionAnswerRequest): ResponseEntity<Any>
}