package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.user.service.UserService
import kz.iitu.uiren.ui.dto.user.request.CredentialsRequest
import kz.iitu.uiren.utils.TokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var tokenFilter: TokenFilter

    @PostMapping("/sign-up")
    @Throws(Exception::class)
    fun register(
        @RequestBody credentialsRequest: CredentialsRequest
    ): ResponseEntity<Any> = userService.register(credentialsRequest)

    @PostMapping("/sign-in")
    @Throws(Exception::class)
    fun login(
        @RequestBody credentialsRequest: CredentialsRequest
    ): ResponseEntity<Any> = userService.login(credentialsRequest)

    @GetMapping("/profile")
    @Throws(Exception::class)
    fun getProfile(
        @RequestHeader(TokenFilter.HEADER) token: String
    ): ResponseEntity<Any> = tokenFilter.checkToken(token) {
        ResponseEntity.ok(it)
    }
}
