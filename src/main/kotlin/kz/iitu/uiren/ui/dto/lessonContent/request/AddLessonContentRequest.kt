package kz.iitu.uiren.ui.dto.lessonContent.request

class AddLessonContentRequest(
    val name: String,
    val description: String,
    val imageUrl: String,
    val audioUrl: String,
    val lessonId: Long
)