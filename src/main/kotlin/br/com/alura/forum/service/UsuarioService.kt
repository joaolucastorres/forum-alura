package br.com.alura.forum.service

import br.com.alura.forum.config.JWTUtils
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val authManager: AuthenticationManager,
    private val jwtUtils: JWTUtils
){
    fun buscarPorId(id: Long): Usuario {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("Usuário não encontrado")
    }

    fun authenticateAndGenerateToken(username: String, password: String): String {
        val token = UsernamePasswordAuthenticationToken(username, password)
        val authResult: Authentication = authManager.authenticate(token)
        val user = authResult.principal as UserDetail
        return jwtUtils.generateToken(user.username, user.authorities)
    }



}
