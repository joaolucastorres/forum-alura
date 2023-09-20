package br.com.alura.forum.service

import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(private val repository: UsuarioRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return repository.findByEmail(username).run {
            this ?: throw RuntimeException()
            UserDetail(this)
        }
    }
}