package br.com.alura.forum.mapper

import br.com.alura.forum.dto.CadastroTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.ServiceCurso
import br.com.alura.forum.service.ServiceUsuario
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class TopicoFormMapper(
    private var serviceCurso: ServiceCurso,
    private var serviceUsuario: ServiceUsuario
): Mapper<CadastroTopicoForm, Topico > {
    override fun map(t: CadastroTopicoForm): Topico {
        return Topico(
            id = Random.nextLong(50),
            titulo = t.titulo,
            mensagem = t.mensagem,
            autor = serviceUsuario.buscarPorId(t.idAutor),
            curso = serviceCurso.buscarPorId(t.idCurso)
        )
    }
}