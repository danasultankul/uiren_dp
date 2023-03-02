package kz.iitu.uiren.domain.lessonContent.service

import kz.iitu.uiren.domain.lessonContent.model.LessonContent
import kz.iitu.uiren.domain.lessonContent.repo.LessonContentRepo
import kz.iitu.uiren.ui.dto.lessonContent.request.AddLessonContentRequest
import kz.iitu.uiren.ui.dto.lessonContent.request.UpdateLessonContentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LessonContentService : ILessonContentService {

    @Autowired
    private lateinit var lessonContentRepo: LessonContentRepo

    override fun addLessonContent(addLessonContentRequest: AddLessonContentRequest): ResponseEntity<Any> {
        lessonContentRepo.save(
            LessonContent(
                name = addLessonContentRequest.name,
                description = addLessonContentRequest.description,
                imageUrl = addLessonContentRequest.imageUrl,
                audioUrl = addLessonContentRequest.audioUrl,
                lessonId = addLessonContentRequest.lessonId
            )
        )
        return ResponseEntity.ok("Saved")
    }

    override fun updateLessonContent(updateLessonContentRequest: UpdateLessonContentRequest): ResponseEntity<Any> {
        val contentOptional = lessonContentRepo.findById(updateLessonContentRequest.id)
        if (contentOptional.isPresent) {
            val content = contentOptional.get()
            content.name = updateLessonContentRequest.name
            content.description = updateLessonContentRequest.description
            content.imageUrl = updateLessonContentRequest.imageUrl
            content.audioUrl = updateLessonContentRequest.audioUrl
            lessonContentRepo.save(content)
            return ResponseEntity.ok("Updated")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson content not found")
    }

    override fun deleteLessonContent(lessonContentId: Long): ResponseEntity<Any> {
        val contentOptional = lessonContentRepo.findById(lessonContentId)
        if (contentOptional.isPresent) {
            lessonContentRepo.delete(contentOptional.get())
            return ResponseEntity.ok("Deleted")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson content not found")
    }

    override fun getById(id: Long): ResponseEntity<Any> {
        val contentOptional = lessonContentRepo.findById(id)
        if (!contentOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson content not found")
        }
        return ResponseEntity.ok(contentOptional.get())
    }

    override fun findAllByLesson(lessonId: Long): ResponseEntity<Any> = ResponseEntity.ok(
        lessonContentRepo.findAllByLessonId(lessonId)
    )
}