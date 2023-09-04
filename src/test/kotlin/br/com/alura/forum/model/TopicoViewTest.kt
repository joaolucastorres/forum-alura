package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicoView
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id= 1,
        titulo = "Teste TopicoView",
        mensagem = "Teste TopicoView",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = null
    )
}