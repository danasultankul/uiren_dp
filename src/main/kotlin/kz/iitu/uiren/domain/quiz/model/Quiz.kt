package kz.iitu.uiren.domain.quiz.model

import javax.persistence.*

@Entity
@Table(name = "quiz")
class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    val type: String? = null,
    val forId: Long? = null
)