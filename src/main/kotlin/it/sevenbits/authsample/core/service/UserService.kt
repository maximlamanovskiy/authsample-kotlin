package it.sevenbits.authsample.core.service

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import it.sevenbits.authsample.core.exception.LoginFailedException
import it.sevenbits.authsample.core.model.UserModel
import it.sevenbits.authsample.core.repository.UserRepository
import it.sevenbits.authsample.web.model.LoginRequest
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    val argon2: Argon2 = Argon2Factory.create()

    fun checkUser(loginRequest: LoginRequest): UserModel {
        userRepository.getUser(loginRequest.login)?.let {
            val requestPassword = loginRequest.password
            val userHashedPassword = it.password
            if (argon2.verify(userHashedPassword, requestPassword.toCharArray())) return it
        }
        throw LoginFailedException("Invalid login or password")
    }
}