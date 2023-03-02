package kz.iitu.uiren.ui.dto.answer.request

class AddAnswerRequest(
    val content: String? = null,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val questionId: Long,
    val isRight: Boolean = false
)