package br.com.alura.forum.mapper

import br.com.alura.forum.dto.CadastroRespostaForm
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.TopicoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private var usuarioService: UsuarioService,
    private val topicoService: TopicoService
): Mapper<CadastroRespostaForm, Resposta> {
    override fun map(t: CadastroRespostaForm): Resposta {
        return Resposta(
            mensagem = t.mensagem,
            autor = usuarioService.buscarPorId(t.idAutor),
            topico = topicoService.buscarTopicoPorId(t.idTopico),
            solucao = t.solucao
        )
    }
}