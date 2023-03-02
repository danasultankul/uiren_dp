package kz.iitu.uiren.domain.lesson.model

import javax.persistence.*

@Entity
@Table(name = "lesson")
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    val courseId: Long? = null
)