package kz.iitu.uiren.domain.confirmation.service

import kz.iitu.uiren.ui.dto.confirmation.request.ConfirmRequest
import org.springframework.http.ResponseEntity

interface IConfirmationService {

    fun confirmRegister(confirmRequest: ConfirmRequest): ResponseEntity<Any>

}