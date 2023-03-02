package kz.iitu.uiren.domain.confirmation.model

import javax.persistence.*

@Entity
@Table(name = "confirmation")
class Confirmation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String? = null,
    val code: String? = null
)