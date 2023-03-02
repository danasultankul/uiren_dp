package kz.iitu.uiren.domain.question.model

import javax.persistence.*

@Entity
@Table(name = "question")
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var content: String? = null,
    var imageUrl: String? = null,
    var audioUrl: String? = null,
    val quizId: Long? = null
)