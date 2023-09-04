package br.com.alura.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 2,
        titulo = "Teste topico",
        mensagem = "Teste topico",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}