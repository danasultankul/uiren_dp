package kz.iitu.uiren.ui.dto.answer.request

class UpdateAnswerRequest(
    val id: Long,
    val content: String? = null,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val isRight: Boolean = false
)