package kz.iitu.uiren.ui.controller

import kz.iitu.uiren.domain.confirmation.service.ConfirmationService
import kz.iitu.uiren.ui.dto.confirmation.request.ConfirmRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
@RequestMapping("/confirmation")
class ConfirmationController {

    @Autowired
    private lateinit var confirmationService: ConfirmationService

    @PostMapping
    @Throws(Exception::class)
    fun confirmRegister(
        @RequestBody confirmRequest: ConfirmRequest
    ): ResponseEntity<Any> = confirmationService.confirmRegister(confirmRequest)

}