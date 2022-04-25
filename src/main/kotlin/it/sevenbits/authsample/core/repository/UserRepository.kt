package it.sevenbits.authsample.core.repository

import it.sevenbits.authsample.core.model.UserModel
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val users: Map<String, UserModel> = mapOf(
        Pair("admin@mail.com", UserModel("1", "admin@mail.com", "\$argon2i\$v=19\$m=16,t=2,p=1\$Q3hEbENpUFZ2Q2Z3emkzRw\$mkoclpxaVvR+zxjPC1fiCQ")),
        Pair("user@mail.com", UserModel("2", "user@mail.com", "\$argon2i\$v=19\$m=16,t=2,p=1\$Rk42Nm9GdWl2Y2lxUkZ5SA\$Q6fFmZn0cvEEz/MZ5zdISA")),
    )

    fun getUser(login: String): UserModel? = users[login]
}
