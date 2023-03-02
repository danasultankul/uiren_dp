package kz.iitu.uiren.domain.lesson.service

import kz.iitu.uiren.domain.course.model.PassedCourse
import kz.iitu.uiren.domain.course.repo.CourseRepo
import kz.iitu.uiren.domain.course.repo.PassedCourseRepo
import kz.iitu.uiren.domain.lesson.model.Lesson
import kz.iitu.uiren.domain.lesson.model.PassedLesson
import kz.iitu.uiren.domain.lesson.repo.LessonRepo
import kz.iitu.uiren.domain.lesson.repo.PassedLessonRepo
import kz.iitu.uiren.domain.quiz.model.QuizType
import kz.iitu.uiren.domain.quiz.repo.QuizRepo
import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.domain.user.repo.UserRepo
import kz.iitu.uiren.ui.dto.lesson.request.AddLessonRequest
import kz.iitu.uiren.ui.dto.lesson.request.UpdateLessonRequest
import kz.iitu.uiren.ui.dto.lesson.response.PassedLessonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LessonService : ILessonService {

    @Autowired
    private lateinit var lessonRepo: LessonRepo

    @Autowired
    private lateinit var courseRepo: CourseRepo

    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var quizRepo: QuizRepo

    @Autowired
    private lateinit var passedLessonRepo: PassedLessonRepo

    @Autowired
    private lateinit var passedCourseRepo: PassedCourseRepo

    override fun addLesson(addLessonRequest: AddLessonRequest): ResponseEntity<Any> {
        if (courseRepo.findById(addLessonRequest.courseId).isPresent) {
            lessonRepo.save(
                Lesson(
                    name = addLessonRequest.name,
                    description = addLessonRequest.description,
                    courseId = addLessonRequest.courseId
                )
            )
            return ResponseEntity.ok("Saved")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found")
    }

    override fun updateLesson(updateLessonRequest: UpdateLessonRequest): ResponseEntity<Any> {
        val lessonOptional = lessonRepo.findById(updateLessonRequest.id)
        if (lessonOptional.isPresent) {
            val lesson = lessonOptional.get()
            lesson.name = updateLessonRequest.name
            lesson.description = updateLessonRequest.description
            lessonRepo.save(lesson)
            return ResponseEntity.ok("Updated")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found")
    }

    override fun deleteLesson(lessonId: Long): ResponseEntity<Any> {
        val lessonOptional = lessonRepo.findById(lessonId)
        if (!lessonOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found")
        }
        lessonRepo.delete(lessonOptional.get())
        return ResponseEntity.ok("Deleted")
    }

    override fun getById(lessonId: Long): ResponseEntity<Any> {
        val lessonOptional = lessonRepo.findById(lessonId)
        if (!lessonOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found")
        }
        return ResponseEntity.ok(lessonOptional.get())
    }

    override fun findAllByCourseId(courseId: Long): ResponseEntity<Any> = ResponseEntity.ok(
        lessonRepo.findAllByCourseId(courseId)
    )

    override fun saveLesson(user: User, lessonId: Long): ResponseEntity<Any> {
        user.lessonId = lessonId
        userRepo.save(user)
        return ResponseEntity.ok("Saved")
    }

    override fun passLesson(user: User, lessonId: Long): ResponseEntity<Any> {
        val lessonOptional = lessonRepo.findById(lessonId)
        if (!lessonOptional.isPresent) {
            return ResponseEntity.badRequest().body("Lesson not found")
        }
        passedLessonRepo.save(
            PassedLesson(
                userId = user.id,
                lessonId = lessonId
            )
        )
        val lesson = lessonOptional.get()
        val lessonQuizOptional = quizRepo.findByForIdAndType(lessonId, QuizType.LESSON.name)
        if (lessonQuizOptional.isPresent) {
            user.lessonId = null
            user.quizId = lessonQuizOptional.get().id
            userRepo.save(user)
            return ResponseEntity.ok("Start quiz")
        }
        val lessons = lessonRepo.findAllByCourseId(lesson.courseId!!)
        for (i in lessons.indices) {
            if (lessons[i].id == lesson.id) {
                if (i < lessons.size - 1) {
                    user.lessonId = lessons[i + 1].id
                    userRepo.save(user)
                    return ResponseEntity.ok("Start next lesson")
                }
            }
        }
        val courseQuizOptional = quizRepo.findByForIdAndType(lesson.courseId, QuizType.COURSE.name)
        if (!courseQuizOptional.isPresent) {
            user.lessonId = null
            user.quizId = null
            user.courseId = null
            userRepo.save(user)
            passedCourseRepo.save(
                PassedCourse(
                    userId = user.id,
                    courseId = lesson.courseId
                )
            )
            return ResponseEntity.ok("Course finished")
        }
        user.lessonId = null
        user.quizId = courseQuizOptional.get().id
        userRepo.save(user)
        return ResponseEntity.ok("Start course quiz")
    }

    override fun findAllPassedLessons(user: User): ResponseEntity<Any> {
        val lessonsPassed = passedLessonRepo.findAllByUserId(user.id!!)
        val response = arrayListOf<PassedLessonResponse>()
        for (lessonPassed in lessonsPassed) {
            val lesson = lessonRepo.findById(lessonPassed.lessonId!!)
            if (lesson.isPresent) {
                response.add(
                    PassedLessonResponse(
                        time = lessonPassed.time,
                        lesson = lesson.get()
                    )
                )
            }
        }
        return ResponseEntity.ok(response)
    }

}