package kz.iitu.uiren.domain.question.service

import kz.iitu.uiren.ui.dto.question.request.AddQuestionRequest
import kz.iitu.uiren.ui.dto.question.request.UpdateQuestionRequest
import org.springframework.http.ResponseEntity

interface IQuestionService {

    fun addQuestion(addQuestionRequest: AddQuestionRequest): ResponseEntity<Any>

    fun updateQuestion(updateQuestionRequest: UpdateQuestionRequest): ResponseEntity<Any>

    fun getQuestions(quizId: Long): ResponseEntity<Any>

    fun deleteQuestion(questionId: Long): ResponseEntity<Any>

}