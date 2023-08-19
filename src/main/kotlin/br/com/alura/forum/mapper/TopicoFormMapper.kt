package br.com.alura.forum.mapper

import br.com.alura.forum.dto.CadastroTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private var cursoService: CursoService,
    private var usuarioService: UsuarioService
): Mapper<CadastroTopicoForm, Topico > {
    override fun map(t: CadastroTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            autor = usuarioService.buscarPorId(t.idAutor),
            curso = cursoService.buscarPorId(t.idCurso)
        )
    }
}