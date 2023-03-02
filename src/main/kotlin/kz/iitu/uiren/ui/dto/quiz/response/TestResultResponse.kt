package kz.iitu.uiren.ui.dto.quiz.response

import kz.iitu.uiren.domain.quiz.model.Quiz

class TestResultResponse(
    val total: Int,
    val correct: Int,
    val passedTime: Long,
    val quiz: Quiz
)