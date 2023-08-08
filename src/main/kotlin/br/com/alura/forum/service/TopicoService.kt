package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.CadastroTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var repository: TopicoRepository,
    private var topicoFormMapper: TopicoFormMapper,
    private var topicoViewMapper: TopicoViewMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {

    fun listar(): List<TopicoView> {
        return repository.findAll().map { t ->
            topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        return repository.findByIdOrNull(id).run {
            this ?: throw NotFoundException(notFoundMessage)
            topicoViewMapper.map(this)
        }
    }

    fun cadastrar(topico: CadastroTopicoForm): TopicoView {
        return topicoFormMapper.map(topico).run {
            repository.save(this)
            topicoViewMapper.map(this)
        }
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        return repository.findByIdOrNull(form.id)
            .run {
                this ?: throw NotFoundException(notFoundMessage)
                mensagem = form.mensagem
                titulo = form.titulo
                topicoViewMapper.map(this)
            }
    }

    fun deletar(id: Long) {
        repository.findByIdOrNull(id).run {
            this ?: throw NotFoundException(notFoundMessage)
            repository.deleteById(id)
        }
    }
}