package kz.iitu.uiren.domain.course.service

import kz.iitu.uiren.domain.course.model.Course
import kz.iitu.uiren.domain.course.repo.CourseRepo
import kz.iitu.uiren.domain.course.repo.PassedCourseRepo
import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.ui.dto.course.request.AddCourseRequest
import kz.iitu.uiren.ui.dto.course.request.UpdateCourseRequest
import kz.iitu.uiren.ui.dto.course.response.PassedCourseResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CourseService : ICourseService {

    @Autowired
    private lateinit var courseRepo: CourseRepo

    @Autowired
    private lateinit var passedCourseRepo: PassedCourseRepo

    override fun addCourse(addCourseRequest: AddCourseRequest): ResponseEntity<Any> {
        courseRepo.save(
            Course(
                name = addCourseRequest.name,
                description = addCourseRequest.description
            )
        )
        return ResponseEntity.ok("Saved")
    }

    override fun updateCourse(updateCourseRequest: UpdateCourseRequest): ResponseEntity<Any> {
        val courseOptional = courseRepo.findById(updateCourseRequest.id)
        if (!courseOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found")
        }
        val course = courseOptional.get()
        course.name = updateCourseRequest.name
        course.description = updateCourseRequest.description
        courseRepo.save(course)
        return ResponseEntity.ok("Updated")
    }

    override fun deleteCourse(courseId: Long): ResponseEntity<Any> {
        val courseOptional = courseRepo.findById(courseId)
        if (!courseOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found")
        }
        courseRepo.delete(courseOptional.get())
        return ResponseEntity.ok("Deleted")
    }

    override fun getById(courseId: Long): ResponseEntity<Any> {
        val courseOptional = courseRepo.findById(courseId)
        if (courseOptional.isPresent) {
            return ResponseEntity.ok(courseOptional.get())
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found")
    }

    override fun findAll(): ResponseEntity<Any> = ResponseEntity.ok(courseRepo.findAll())

    override fun saveCourse(user: User, courseId: Long): ResponseEntity<Any> {
        user.courseId = courseId
        return ResponseEntity.ok("Saved")
    }

    override fun findAllPassedCourses(user: User): ResponseEntity<Any> {
        val response = arrayListOf<PassedCourseResponse>()
        val coursesPassed = passedCourseRepo.findAllByUserId(user.id!!)
        for (coursePassed in coursesPassed) {
            val course = courseRepo.findById(coursePassed.courseId!!)
            if (course.isPresent) {
                response.add(
                    PassedCourseResponse(
                        time = coursePassed.time,
                        course = course.get()
                    )
                )
            }
        }
        return ResponseEntity.ok(response)
    }

}