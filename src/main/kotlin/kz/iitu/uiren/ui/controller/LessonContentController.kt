package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.lessonContent.service.LessonContentService
import kz.iitu.uiren.ui.dto.lessonContent.request.AddLessonContentRequest
import kz.iitu.uiren.ui.dto.lessonContent.request.UpdateLessonContentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
@RequestMapping("/lesson-content")
class LessonContentController {

    @Autowired
    private lateinit var lessonContentService: LessonContentService

    @PostMapping("/add")
    @Throws(Exception::class)
    fun addLessonContent(
        @RequestBody addLessonContentRequest: AddLessonContentRequest
    ): ResponseEntity<Any> = lessonContentService.addLessonContent(addLessonContentRequest)

    @PostMapping("/update")
    @Throws(Exception::class)
    fun updateLessonContent(
        @RequestBody updateLessonContentRequest: UpdateLessonContentRequest
    ): ResponseEntity<Any> = lessonContentService.updateLessonContent(updateLessonContentRequest)

    @DeleteMapping
    @Throws(Exception::class)
    fun deleteLessonContent(
        @RequestParam("id") lessonContentId: Long
    ): ResponseEntity<Any> = lessonContentService.deleteLessonContent(lessonContentId)

    @GetMapping("/by-id")
    @Throws(Exception::class)
    fun getById(
        @RequestParam("id") id: Long
    ): ResponseEntity<Any> = lessonContentService.getById(id)

    @GetMapping("/by-lesson-id")
    @Throws(Exception::class)
    fun findAllByLesson(
        @RequestParam("lessonId") lessonId: Long
    ): ResponseEntity<Any> = lessonContentService.findAllByLesson(lessonId)
}