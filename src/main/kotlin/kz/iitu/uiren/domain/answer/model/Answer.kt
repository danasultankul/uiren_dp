package kz.iitu.uiren.domain.answer.model

import javax.persistence.*

@Entity
@Table(name = "answer")
class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var content: String? = null,
    var imageUrl: String? = null,
    var audioUrl: String? = null,
    val questionId: Long? = null,
    var isRight: Boolean = false
)