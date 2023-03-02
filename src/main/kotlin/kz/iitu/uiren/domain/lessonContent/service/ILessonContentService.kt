package kz.iitu.uiren.domain.lessonContent.service

import kz.iitu.uiren.ui.dto.lessonContent.request.AddLessonContentRequest
import kz.iitu.uiren.ui.dto.lessonContent.request.UpdateLessonContentRequest
import org.springframework.http.ResponseEntity

interface ILessonContentService {

    fun addLessonContent(addLessonContentRequest: AddLessonContentRequest): ResponseEntity<Any>

    fun updateLessonContent(updateLessonContentRequest: UpdateLessonContentRequest): ResponseEntity<Any>

    fun deleteLessonContent(lessonContentId: Long): ResponseEntity<Any>

    fun getById(id: Long): ResponseEntity<Any>

    fun findAllByLesson(lessonId: Long): ResponseEntity<Any>

}