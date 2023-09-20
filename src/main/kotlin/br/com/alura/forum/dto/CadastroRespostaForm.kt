package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CadastroRespostaForm (
    @field:NotEmpty(message = "A mensagem não pode estar em branco")
    @field:Size(min = 5, message = "A mensagem deve ter no mínimo 5 caracteres")
    val mensagem: String,
    @field:NotNull val idAutor: Long,
    @field:NotNull val idTopico: Long,
    val solucao: Boolean = false
)