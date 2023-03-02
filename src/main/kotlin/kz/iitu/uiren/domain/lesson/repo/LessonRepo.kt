package kz.iitu.uiren.domain.lesson.repo

import kz.iitu.uiren.domain.lesson.model.Lesson
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepo : CrudRepository<Lesson, Long> {

    fun findAllByCourseId(courseId: Long): MutableList<Lesson>

}