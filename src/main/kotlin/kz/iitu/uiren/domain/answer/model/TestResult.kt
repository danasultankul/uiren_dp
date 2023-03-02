package kz.iitu.uiren.domain.answer.model

import javax.persistence.*

@Entity
@Table(name = "result_test")
class TestResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long? = null,
    val total: Int? = null,
    val correct: Int? = null,
    val passedTime: Long = System.currentTimeMillis(),
    val quizId: Long? = null
)