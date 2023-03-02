package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.answer.service.AnswerService
import kz.iitu.uiren.ui.dto.answer.request.AddAnswerRequest
import kz.iitu.uiren.ui.dto.answer.request.QuestionAnswerRequest
import kz.iitu.uiren.ui.dto.answer.request.UpdateAnswerRequest
import kz.iitu.uiren.utils.TokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
@RequestMapping("/answer")
class AnswerController {

    @Autowired
    private lateinit var answerService: AnswerService

    @Autowired
    private lateinit var tokenFilter: TokenFilter

    @PostMapping("/add")
    @Throws(Exception::class)
    fun addAnswer(
         @RequestBody addAnswerRequest: AddAnswerRequest
    ): ResponseEntity<Any> = answerService.addAnswer(addAnswerRequest)


    @PostMapping("/update")
    @Throws(Exception::class)
    fun updateAnswer(
        @RequestBody updateAnswerRequest: UpdateAnswerRequest
    ): ResponseEntity<Any> = answerService.updateAnswer(updateAnswerRequest)


    @DeleteMapping
    @Throws(Exception::class)
    fun deleteAnswer(
        @RequestParam("id") answerId: Long
    ): ResponseEntity<Any> = answerService.deleteAnswer(answerId)


    @GetMapping
    @Throws(Exception::class)
    fun findAllAnswers(
        @RequestParam("questionId") questionId: Long
    ): ResponseEntity<Any> = answerService.findAllAnswers(questionId)


    @GetMapping("/results")
    @Throws(Exception::class)
    fun findAllTestResults(
        @RequestHeader(TokenFilter.HEADER) token: String
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        answerService.findAllTestResults(it)
    }

    @PostMapping("/pass")
    @Throws(Exception::class)
    fun passQuiz(
        @RequestHeader(TokenFilter.HEADER) token: String,
        @RequestBody answers: QuestionAnswerRequest
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        answerService.passQuiz(it, answers)
    }

}