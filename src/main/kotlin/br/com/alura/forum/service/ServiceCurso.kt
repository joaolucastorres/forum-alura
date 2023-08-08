package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.CursoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServiceCurso(private val repository: CursoRepository) {
    fun buscarPorId(id: Long): Curso {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("Curso não encontrado")
    }

}
