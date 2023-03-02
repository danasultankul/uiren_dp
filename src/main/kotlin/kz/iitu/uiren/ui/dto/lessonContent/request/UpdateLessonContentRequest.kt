package kz.iitu.uiren.ui.dto.lessonContent.request

class UpdateLessonContentRequest(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val audioUrl: String,
)