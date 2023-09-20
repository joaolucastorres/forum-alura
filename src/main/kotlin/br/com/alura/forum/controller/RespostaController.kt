package br.com.alura.forum.controller

import br.com.alura.forum.dto.CadastroRespostaForm
import br.com.alura.forum.dto.CadastroTopicoForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@SecurityRequirement(
    name = "bearerAuth"
)
@RequestMapping("/respostas")
class RespostaController (
    private val respostaService: RespostaService
){
    @GetMapping("/{idTopico}")
    fun listar(
        @PathVariable
        idTopico: Long,
        @PageableDefault(
            size = 5,
            sort = ["dataCriacao"],
            direction = Sort.Direction.DESC
        )paginacao: Pageable
    ): Page<RespostaView> {
        return respostaService.listar(idTopico, paginacao)
    }

    @PostMapping
    @Transactional
    fun cadastrar(uriBuilder: UriComponentsBuilder,
                  @RequestBody @Valid form: CadastroRespostaForm
    ): ResponseEntity<RespostaView> {
        return respostaService.cadastrar(form).run {
            ResponseEntity.created(uriBuilder.path("/respostas/${this.id}").build().toUri())
                .body(this)
        }
    }
}