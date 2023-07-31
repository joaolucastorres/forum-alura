package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.CadastroTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: MutableList<Topico> = mutableListOf(),
    private var topicoFormMapper: TopicoFormMapper,
    private var topicoViewMapper: TopicoViewMapper
) {

    fun listar(): List<TopicoView> {
        return topicos.map { t ->
            topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        return topicos.map { t ->
            topicoViewMapper.map(t)
        }.first { t ->
            t.id == id
        }
    }

    fun cadastrar(topico: CadastroTopicoForm) {
        topicos.add(topicoFormMapper.map(topico))
    }

    fun atualizar(form: AtualizacaoTopicoForm) {
        topicos.first { t ->
            t.id == form.id
        }.run {
            topicos.remove(this)
            topicos.add(Topico(
                id= form.id,
                autor = autor,
                respostas = respostas,
                curso = curso,
                dataCriacao = dataCriacao,
                titulo = form.titulo,
                status = status,
                mensagem = form.mensagem
            ))
        }
    }
}