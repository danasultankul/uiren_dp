package kz.iitu.uiren.utils

import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.domain.user.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TokenFilter {

    companion object {
        const val HEADER = "Authorization"
    }

    @Autowired
    private lateinit var userRepo: UserRepo

    fun checkToken(token: String, allowed: ((User) -> ResponseEntity<Any>)): ResponseEntity<Any> {
        val userOptional = userRepo.findByToken(token)
        if (userOptional.isPresent) {
            return allowed.invoke(userOptional.get())
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized")
    }


}