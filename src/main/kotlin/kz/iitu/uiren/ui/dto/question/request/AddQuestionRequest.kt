package kz.iitu.uiren.ui.dto.question.request

class AddQuestionRequest(
    val content: String? = null,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val quizId: Long
)