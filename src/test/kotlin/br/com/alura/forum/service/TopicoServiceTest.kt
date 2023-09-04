package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {
    val topicos = PageImpl(listOf(TopicoTest.build()))
    val paginacao: Pageable = mockk()

    private var repository: TopicoRepository = mockk {
        every {
            findByCursoNome(any(), any())
        } returns topicos
        every {
            findAll(paginacao)
        } returns topicos
    }
    private var topicoFormMapper: TopicoFormMapper = mockk()
    private var topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    private var topicoService = TopicoService(
        repository = repository,
        topicoFormMapper = topicoFormMapper,
        topicoViewMapper = topicoViewMapper
    )


    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.listar("Teste curso", paginacao)

        verify(exactly = 1) {
            repository.findByCursoNome(any(), any())
        }
        verify(exactly = 1) {
            topicoViewMapper.map(any())
        }
        verify(exactly = 0) {
            repository.findAll(paginacao)
        }
    }

    @Test
    fun `deve listar todos os cursos quando o nome do curso for nulo`() {
        topicoService.listar(null, paginacao)

        verify(exactly = 0) {
            repository.findByCursoNome(any(), any())
        }
        verify(exactly = 1) {
            topicoViewMapper.map(any())
        }
        verify(exactly = 1) {
            repository.findAll(paginacao)
        }
    }

    @Test
    fun `deve listar not found quando o topico nao for encontrado`() {
        every { repository.findById(1) } returns Optional.empty()
        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }
        assertThat(atual.message).isEqualTo("Tópico não encontrado")
    }
}


