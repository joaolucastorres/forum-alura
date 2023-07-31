package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CadastroTopicoForm (
    @field:NotEmpty @Size(min = 5) val titulo: String,
    @field:NotEmpty @Size(min = 5) val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
)