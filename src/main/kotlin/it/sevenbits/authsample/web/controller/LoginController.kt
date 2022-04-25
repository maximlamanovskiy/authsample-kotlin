package it.sevenbits.authsample.web.controller

import it.sevenbits.authsample.core.service.JwtService
import it.sevenbits.authsample.core.service.UserService
import it.sevenbits.authsample.web.model.LoginRequest
import it.sevenbits.authsample.web.model.LoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController(private val userService: UserService, private val jwtService: JwtService) {

    @PostMapping
    @ResponseBody
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        userService.checkUser(loginRequest).let {
            return ResponseEntity.ok(jwtService.createToken(it))
        }
    }
}