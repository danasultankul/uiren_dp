package kz.iitu.uiren.domain.confirmation.service

import kz.iitu.uiren.domain.confirmation.repo.ConfirmationRepo
import kz.iitu.uiren.domain.user.repo.UserRepo
import kz.iitu.uiren.ui.dto.confirmation.request.ConfirmRequest
import kz.iitu.uiren.utils.JWTCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConfirmationService : IConfirmationService {

    @Autowired
    private lateinit var confirmationRepo: ConfirmationRepo

    @Autowired
    private lateinit var userRepo: UserRepo

    override fun confirmRegister(confirmRequest: ConfirmRequest): ResponseEntity<Any> {
        val confirmationOptional = confirmationRepo.findByEmail(confirmRequest.email)
        if (confirmationOptional.isPresent) {
            val confirmation = confirmationOptional.get()
            return if (confirmation.code == confirmRequest.code) {
                confirmationRepo.delete(confirmation)
                val user = userRepo.findByEmail(confirmRequest.email).get()
                user.token = JWTCreator.generateToken(
                    email = confirmRequest.email,
                    password = user.password!!
                )
                userRepo.save(user)
                ResponseEntity.ok("Confirmed")
            } else {
                ResponseEntity.badRequest().body("Incorrect code")
            }
        }
        return ResponseEntity.badRequest().body("Confirmation not found")
    }

}