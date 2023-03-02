package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.question.service.QuestionService
import kz.iitu.uiren.ui.dto.question.request.AddQuestionRequest
import kz.iitu.uiren.ui.dto.question.request.UpdateQuestionRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
@RequestMapping("/question")
class QuestionController {

    @Autowired
    private lateinit var questionService: QuestionService

    @PutMapping
    @Throws(Exception::class)
    fun addQuestion(
        @RequestBody addQuestionRequest: AddQuestionRequest
    ): ResponseEntity<Any> = questionService.addQuestion(addQuestionRequest)

    @PostMapping
    @Throws(Exception::class)
    fun updateQuestion(
        @RequestBody updateQuestionRequest: UpdateQuestionRequest
    ): ResponseEntity<Any> = questionService.updateQuestion(updateQuestionRequest)

    @GetMapping
    @Throws(Exception::class)
    fun getQuestions(
         @RequestParam("id") quizId: Long
    ): ResponseEntity<Any> = questionService.getQuestions(quizId)

    @DeleteMapping
    @Throws(Exception::class)
    fun deleteQuestion(
        @RequestParam("id") questionId: Long
    ): ResponseEntity<Any> = questionService.deleteQuestion(questionId)
}