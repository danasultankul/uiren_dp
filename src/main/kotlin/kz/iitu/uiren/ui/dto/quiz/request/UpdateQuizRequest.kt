package kz.iitu.uiren.ui.dto.quiz.request

class UpdateQuizRequest(
    val id: Long,
    val name: String? = null,
    val description: String? = null,
)