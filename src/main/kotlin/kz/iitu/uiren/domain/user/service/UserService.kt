package kz.iitu.uiren.domain.user.service

import kz.iitu.uiren.domain.confirmation.model.Confirmation
import kz.iitu.uiren.domain.confirmation.repo.ConfirmationRepo
import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.domain.user.repo.UserRepo
import kz.iitu.uiren.ui.dto.user.request.CredentialsRequest
import kz.iitu.uiren.ui.dto.user.response.TokenResponse
import kz.iitu.uiren.utils.JWTCreator
import kz.iitu.uiren.utils.MailService
import kz.iitu.uiren.utils.SmsService
import kz.iitu.uiren.utils.Validator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Random

@Service
class UserService : IUserService {

    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var smsService: SmsService

    @Autowired
    private lateinit var mailService: MailService

    @Autowired
    private lateinit var validator: Validator

    @Autowired
    private lateinit var confirmationRepo: ConfirmationRepo

    override fun register(credentialsRequest: CredentialsRequest): ResponseEntity<Any> {
        if (!validator.checkEmailOrPhone(credentialsRequest.email)) {
            return ResponseEntity.badRequest().body("Invalid email or phone")
        }
        if (!validator.checkPassword(credentialsRequest.password)) {
            return ResponseEntity.badRequest().body("Invalid password")
        }
        val userOptional = userRepo.findByEmail(credentialsRequest.email)
        if (!userOptional.isPresent) {
            val code = generateCode()
            if (checkIsPhone(credentialsRequest.email)) {
                smsService.sendSms(credentialsRequest.email, code)
            } else {
                mailService.sendCode(credentialsRequest.email, code)
            }
            val confirmationOptional = confirmationRepo.findByEmail(credentialsRequest.email)
            if (confirmationOptional.isPresent) {
                confirmationRepo.delete(confirmationOptional.get())
            }
            confirmationRepo.save(
                Confirmation(
                    email = credentialsRequest.email,
                    code = code
                )
            )
            userRepo.save(
                User(
                    email = credentialsRequest.email,
                    password = credentialsRequest.password
                )
            )
            return ResponseEntity.ok("Code sent")
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Already registered")
    }

    override fun login(credentialsRequest: CredentialsRequest): ResponseEntity<Any> {
        val userOptional = userRepo.findByEmail(credentialsRequest.email)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            return if (user.password == credentialsRequest.password) {
                if (user.token == null) {
                    return ResponseEntity.badRequest().body("Account not confirmed!")
                }
                user.token = JWTCreator.generateToken(
                    email = credentialsRequest.email,
                    password = credentialsRequest.password,
                )
                userRepo.save(user)
                ResponseEntity.ok(
                    TokenResponse(
                        token = user.token!!
                    )
                )
            } else {
                ResponseEntity.badRequest().body("Incorrect password")
            }
        }
        return ResponseEntity.badRequest().body("Email not found")
    }

    private fun checkIsPhone(email: String): Boolean = !email.contains("@")

    private fun generateCode(): String {
        var code = ""
        val random = Random()
        for (i in 0..5) {
            code += random.nextInt(10).toShort()
        }
        return  code
    }
}