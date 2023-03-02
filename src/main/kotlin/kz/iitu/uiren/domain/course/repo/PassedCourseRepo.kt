package kz.iitu.uiren.domain.course.repo

import kz.iitu.uiren.domain.course.model.PassedCourse
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PassedCourseRepo : CrudRepository<PassedCourse, Long> {

    fun findAllByUserId(userId: Long): MutableList<PassedCourse>

}