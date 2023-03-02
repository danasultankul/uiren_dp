package kz.iitu.uiren.domain.user.service

import kz.iitu.uiren.ui.dto.user.request.CredentialsRequest
import org.springframework.http.ResponseEntity

interface IUserService {

    fun register(credentialsRequest: CredentialsRequest): ResponseEntity<Any>

    fun login(credentialsRequest: CredentialsRequest): ResponseEntity<Any>

}