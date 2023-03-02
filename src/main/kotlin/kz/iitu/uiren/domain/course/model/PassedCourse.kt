package kz.iitu.uiren.domain.course.model

import javax.persistence.*

@Entity
@Table(name = "passed_course")
class PassedCourse(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long? = null,
    val courseId: Long? = null,
    val time: Long = System.currentTimeMillis()
)