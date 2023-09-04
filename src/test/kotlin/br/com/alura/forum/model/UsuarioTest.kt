package br.com.alura.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        nome = "Usuario teste",
        email = "emailteste@email.com",
        password = "teste",
        role = listOf(Role(1, "SOMENTE_LEITURA"))
    )
}