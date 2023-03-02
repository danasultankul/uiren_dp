package kz.iitu.uiren.domain.lesson.model

import javax.persistence.*

@Entity
@Table(name = "passed_lesson")
class PassedLesson(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long? = null,
    val lessonId: Long? = null,
    val time: Long = System.currentTimeMillis()
)