package kz.iitu.uiren.ui.dto.lesson.response

import kz.iitu.uiren.domain.lesson.model.Lesson

class PassedLessonResponse(
    val time: Long,
    val lesson: Lesson
)