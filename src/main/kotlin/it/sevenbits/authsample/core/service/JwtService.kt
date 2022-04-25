package it.sevenbits.authsample.core.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import it.sevenbits.authsample.core.model.UserModel
import it.sevenbits.authsample.web.model.LoginResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

@Service
class JwtService(
    private val algorithm: Algorithm,
    @Value("\${jwt.issuer}") private val issuer: String,
    @Value("\${jwt.tokenTtl}") private val ttl: Long,
) {
    companion object {
        const val USER_ID: String = "userId"
    }

    fun createToken(userModel: UserModel): LoginResponse = LoginResponse(
        JWT.create()
            .withIssuer(issuer)
            .withIssuedAt(Date.from(Instant.now()))
            .withExpiresAt(Date.from(Instant.now().plusSeconds(ttl)))
            .withClaim(USER_ID, userModel.id)
            .sign(algorithm)
    )
}