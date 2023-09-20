package br.com.alura.forum.service

import br.com.alura.forum.dto.CadastroRespostaForm
import br.com.alura.forum.dto.CadastroTopicoForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.repository.RespostaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RespostaService (
    private val respostaRepository: RespostaRepository,
    private val respostaFormMapper: RespostaFormMapper,
    private val respostaViewMapper: RespostaViewMapper
) {
    fun listar(idTopico: Long, paginacao: Pageable): Page<RespostaView> {
        return respostaRepository.findByTopicoId(idTopico, paginacao).run{
            map { t ->
                respostaViewMapper.map(t)
            }
        }
    }

    fun cadastrar(resposta: CadastroRespostaForm): RespostaView {
        return respostaFormMapper.map(resposta).run {
            respostaRepository.save(this)
            respostaViewMapper.map(this)
        }
    }

    fun buscarPorId(id: Long): RespostaView {
        return respostaRepository.findByIdOrNull(id).run {
            this ?: throw NotFoundException("Resposta não encontrada!")
            respostaViewMapper.map(this)
        }
    }
}