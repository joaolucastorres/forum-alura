package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServiceUsuario(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("Usuário não encontrado")
    }

}
