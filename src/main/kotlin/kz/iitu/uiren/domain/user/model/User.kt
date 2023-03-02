package kz.iitu.uiren.domain.user.model

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String? = null,
    val password: String? = null,
    var token: String? = null,
    var lessonId: Long? = null,
    var quizId: Long? = null,
    var courseId: Long? = null
)