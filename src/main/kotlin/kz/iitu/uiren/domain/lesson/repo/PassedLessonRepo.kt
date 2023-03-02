package kz.iitu.uiren.domain.lesson.repo

import kz.iitu.uiren.domain.lesson.model.PassedLesson
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PassedLessonRepo : CrudRepository<PassedLesson, Long> {

    fun findAllByUserId(userId: Long): MutableList<PassedLesson>

}