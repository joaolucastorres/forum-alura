package br.com.alura.forum.controller

import br.com.alura.forum.model.Credentials
import br.com.alura.forum.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class UsuarioController (
    private val service: UsuarioService
) {

    @PostMapping("/login")
    fun login(@RequestBody credentials: Credentials): ResponseEntity<Any> {
        val token = service.authenticateAndGenerateToken(credentials.username, credentials.password)
        return ResponseEntity.ok(mapOf("token" to token))
    }

    @PostMapping("/register")
    fun register(@RequestBody credentials: Credentials){

    }




}