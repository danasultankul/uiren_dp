package kz.iitu.uiren.domain.lesson.service

import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.ui.dto.lesson.request.AddLessonRequest
import kz.iitu.uiren.ui.dto.lesson.request.UpdateLessonRequest
import org.springframework.http.ResponseEntity

interface ILessonService {

    fun addLesson(addLessonRequest: AddLessonRequest): ResponseEntity<Any>

    fun updateLesson(updateLessonRequest: UpdateLessonRequest): ResponseEntity<Any>

    fun deleteLesson(lessonId: Long): ResponseEntity<Any>

    fun getById(lessonId: Long): ResponseEntity<Any>

    fun findAllByCourseId(courseId: Long): ResponseEntity<Any>

    fun saveLesson(user: User, lessonId: Long): ResponseEntity<Any>

    fun passLesson(user: User, lessonId: Long): ResponseEntity<Any>

    fun findAllPassedLessons(user: User): ResponseEntity<Any>

}