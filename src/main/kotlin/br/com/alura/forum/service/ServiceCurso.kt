package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class ServiceCurso(private var cursos: MutableList<Curso> = mutableListOf()) {

    init {
        Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
            ).run {
                cursos.add(this)
        }
        Curso(
            id = 2,
            nome = "Java",
            categoria = "Programação"
        ).run {
            cursos.add(this)
        }
    }


    fun buscarPorId(id: Long): Curso {
        return cursos.first{c->
            c.id == id
        }
    }

}
