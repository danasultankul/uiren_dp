package kz.iitu.uiren.domain.lessonContent.model

import javax.persistence.*

@Entity
@Table(name = "lesson_content")
class LessonContent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var audioUrl: String? = null,
    val lessonId: Long? = null,
)