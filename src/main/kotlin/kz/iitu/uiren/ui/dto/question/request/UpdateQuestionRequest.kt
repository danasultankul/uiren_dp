package kz.iitu.uiren.ui.dto.question.request

class UpdateQuestionRequest(
    val id: Long,
    val content: String? = null,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
)