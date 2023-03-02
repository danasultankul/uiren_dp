package kz.iitu.uiren.domain.confirmation.repo

import kz.iitu.uiren.domain.confirmation.model.Confirmation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ConfirmationRepo : CrudRepository<Confirmation, Long> {

    fun findByEmail(email: String): Optional<Confirmation>

}