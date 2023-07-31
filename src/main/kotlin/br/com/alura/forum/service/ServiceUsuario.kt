package br.com.alura.forum.service

import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class ServiceUsuario(private var usuarios: MutableList<Usuario> = mutableListOf()) {

    init {
        Usuario(
            id = 1,
            nome = "João",
            email = "joao@email.com"
        ).run {
            usuarios.add(this)
        }
        Usuario(
            id = 2,
            nome = "Ana",
            email = "ana@email.com"
        ).run {
            usuarios.add(this)
        }
    }

fun buscarPorId(id: Long): Usuario {
    return usuarios.first{ u->
        u.id == id
    }
}

}
