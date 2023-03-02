package kz.iitu.uiren.ui.dto.course.response

import kz.iitu.uiren.domain.course.model.Course

class PassedCourseResponse(
    val time: Long,
    val course: Course
)