package it.sevenbits.authsample.config

import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JwtConfig {
    @Bean
    @Primary
    fun algorithm(@Value("\${jwt.key}") secret: String): Algorithm = Algorithm.HMAC256(secret)
}