package kz.iitu.uiren.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

object JWTCreator {

    fun generateToken(email: String, password: String) : String = JWT.create().withHeader(
        mapOf(
            "email" to email,
            "password" to password,
            "time" to System.currentTimeMillis()
        )
    ).sign(
        Algorithm.HMAC256("Uiren")
    )

}