package kz.iitu.uiren.domain.lessonContent.repo

import kz.iitu.uiren.domain.lessonContent.model.LessonContent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonContentRepo : CrudRepository<LessonContent, Long> {

    fun findAllByLessonId(lessonId: Long): MutableList<LessonContent>

}