package kz.iitu.uiren.utils

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class Validator {

    fun checkEmailOrPhone(email: String): Boolean = if (email.contains("@")) {
        Pattern.matches("^(.+)@(\\\\S+)\$", email)
    } else {
        Pattern.matches("(([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?( ([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?)+)", email)
    }

    fun checkPassword(password: String): Boolean = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$", password)

}