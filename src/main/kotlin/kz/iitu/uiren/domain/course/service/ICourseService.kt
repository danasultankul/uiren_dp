package kz.iitu.uiren.domain.course.service

import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.ui.dto.course.request.AddCourseRequest
import kz.iitu.uiren.ui.dto.course.request.UpdateCourseRequest
import org.springframework.http.ResponseEntity

interface ICourseService {

    fun addCourse(addCourseRequest: AddCourseRequest): ResponseEntity<Any>

    fun updateCourse(updateCourseRequest: UpdateCourseRequest): ResponseEntity<Any>

    fun deleteCourse(courseId: Long): ResponseEntity<Any>

    fun getById(courseId: Long): ResponseEntity<Any>

    fun findAll(): ResponseEntity<Any>

    fun saveCourse(user: User, courseId: Long): ResponseEntity<Any>

    fun findAllPassedCourses(user: User): ResponseEntity<Any>
}