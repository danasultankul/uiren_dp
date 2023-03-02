package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.lesson.service.LessonService
import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.ui.dto.lesson.request.AddLessonRequest
import kz.iitu.uiren.ui.dto.lesson.request.UpdateLessonRequest
import kz.iitu.uiren.utils.TokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.jvm.Throws

@RestController
@RequestMapping("/lesson")
class LessonController {

    @Autowired
    private lateinit var lessonService: LessonService

    @Autowired
    private lateinit var tokenFilter: TokenFilter

    @PostMapping("/add")
    @Throws(Exception::class)
    fun addLesson(
        @RequestBody addLessonRequest: AddLessonRequest
    ): ResponseEntity<Any> = lessonService.addLesson(addLessonRequest)

    @PostMapping("/update")
    @Throws(Exception::class)
    fun updateLesson(
        @RequestBody updateLessonRequest: UpdateLessonRequest
    ): ResponseEntity<Any> = lessonService.updateLesson(updateLessonRequest)

    @DeleteMapping
    @Throws(Exception::class)
    fun deleteLesson(
        @RequestParam("lessonId") lessonId: Long
    ): ResponseEntity<Any> = lessonService.deleteLesson(lessonId)

    @GetMapping("/by-id")
    @Throws(Exception::class)
    fun getById(
        @RequestParam("lessonId") lessonId: Long
    ): ResponseEntity<Any> = lessonService.getById(lessonId)

    @GetMapping("/by-course-id")
    @Throws(Exception::class)
    fun findAllByCourseId(
        @RequestParam("courseId") courseId: Long
    ): ResponseEntity<Any> = lessonService.findAllByCourseId(courseId)

    @PostMapping("/save")
    @Throws(Exception::class)
    fun saveLesson(
        @RequestHeader(TokenFilter.HEADER) token: String,
        @RequestBody lessonId: Long
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        lessonService.saveLesson(it, lessonId)
    }

    @PostMapping("/pass")
    @Throws(Exception::class)
    fun passLesson(
        @RequestHeader(TokenFilter.HEADER) token: String,
        @RequestBody lessonId: Long
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        lessonService.passLesson(it, lessonId)
    }

    @GetMapping("/passed")
    @Throws(Exception::class)
    fun findAllPassedLessons(
        @RequestHeader(TokenFilter.HEADER) token: String
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        lessonService.findAllPassedLessons(it)
    }

}