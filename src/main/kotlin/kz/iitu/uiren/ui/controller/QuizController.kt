package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.quiz.service.QuizService
import kz.iitu.uiren.ui.dto.quiz.request.AddQuizRequest
import kz.iitu.uiren.ui.dto.quiz.request.UpdateQuizRequest
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
@RequestMapping("/quiz")
class QuizController {

    @Autowired
    private lateinit var quizService: QuizService

    @Autowired
    private lateinit var tokenFilter: TokenFilter

    @PostMapping("/add-lesson")
    @Throws(Exception::class)
    fun addLessonQuiz(
        @RequestBody addQuizRequest: AddQuizRequest
    ): ResponseEntity<Any> = quizService.addLessonQuiz(addQuizRequest)

    @PostMapping("/add-course")
    @Throws(Exception::class)
    fun addCourseQuiz(
        @RequestBody addQuizRequest: AddQuizRequest
    ): ResponseEntity<Any> = quizService.addCourseQuiz(addQuizRequest)

    @PostMapping("/update")
    @Throws(Exception::class)
    fun updateQuiz(
        @RequestBody updateQuizRequest: UpdateQuizRequest
    ): ResponseEntity<Any> = quizService.updateQuiz(updateQuizRequest)

    @DeleteMapping
    @Throws(Exception::class)
    fun deleteQuiz(
        @RequestParam("id") quizId: Long
    ): ResponseEntity<Any> = quizService.deleteQuiz(quizId)

    @GetMapping("/lesson-quiz")
    @Throws(Exception::class)
    fun getLessonQuiz(
        @RequestParam("id") lessonId: Long
    ): ResponseEntity<Any> = quizService.getLessonQuiz(lessonId)

    @GetMapping("/course-quiz")
    @Throws(Exception::class)
    fun getCourseQuiz(
        @RequestParam("id") courseId: Long
    ): ResponseEntity<Any> = quizService.getCourseQuiz(courseId)

    @PostMapping("/save")
    @Throws(Exception::class)
    fun saveQuiz(
        @RequestHeader(TokenFilter.HEADER) token: String,
        @RequestBody quizId: Long
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        quizService.saveQuiz(it, quizId)
    }

}
