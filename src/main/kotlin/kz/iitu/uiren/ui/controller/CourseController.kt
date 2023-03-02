package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.course.service.CourseService
import kz.iitu.uiren.ui.dto.course.request.AddCourseRequest
import kz.iitu.uiren.ui.dto.course.request.UpdateCourseRequest
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
@RequestMapping("/course")
class CourseController {

    @Autowired
    private lateinit var courseService: CourseService

    @Autowired
    private lateinit var tokenFilter: TokenFilter

    @PostMapping("/add")
    @Throws(Exception::class)
    fun addCourse(
        @RequestBody addCourseRequest: AddCourseRequest
    ): ResponseEntity<Any> = courseService.addCourse(addCourseRequest)

    @PostMapping("/update")
    @Throws(Exception::class)
    fun updateCourse(
        @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<Any> = courseService.updateCourse(updateCourseRequest)

    @DeleteMapping
    @Throws(Exception::class)
    fun deleteCourse(
        @RequestParam("courseId") courseId: Long
    ): ResponseEntity<Any> = courseService.deleteCourse(courseId)

    @GetMapping("/by-ud")
    @Throws(Exception::class)
    fun getById(
        @RequestParam("courseId") courseId: Long
    ): ResponseEntity<Any> = courseService.getById(courseId)

    @GetMapping("/all")
    @Throws(Exception::class)
    fun findAll(): ResponseEntity<Any> = courseService.findAll()

    @PostMapping("/save")
    @Throws(Exception::class)
    fun saveCourse(
        @RequestHeader(TokenFilter.HEADER) token: String,
        @RequestBody courseId: Long
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        courseService.saveCourse(it, courseId)
    }

    @GetMapping("/passed")
    @Throws(Exception::class)
    fun findAllPassedCourses(
        @RequestHeader(TokenFilter.HEADER) token: String,
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        courseService.findAllPassedCourses(it)
    }

}