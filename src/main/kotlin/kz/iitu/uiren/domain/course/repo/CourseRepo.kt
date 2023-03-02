package kz.iitu.uiren.domain.course.repo

import kz.iitu.uiren.domain.course.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepo : CrudRepository<Course, Long> {

}