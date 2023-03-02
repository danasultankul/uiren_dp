package kz.iitu.uiren.ui.dto.answer.request

class QuestionAnswerRequest(
    val quizId: Long,
    val answers: List<Long>
)