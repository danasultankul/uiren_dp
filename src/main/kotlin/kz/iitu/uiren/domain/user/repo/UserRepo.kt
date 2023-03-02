package kz.iitu.uiren.domain.user.repo

import kz.iitu.uiren.domain.user.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepo : CrudRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByToken(token: String): Optional<User>

}