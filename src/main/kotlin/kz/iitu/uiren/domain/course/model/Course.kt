package kz.iitu.uiren.domain.course.model

import javax.persistence.*

@Entity
@Table(name = "course")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String? = null,
    var description: String? = null
)