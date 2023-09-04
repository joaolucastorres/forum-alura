package br.com.alura.forum.controller

import br.com.alura.forum.config.JWTUtils
import br.com.alura.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jwtUtils: JWTUtils

    private var token: String? = null

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    companion object {
        private const val RECURSO = "/topicos"
    }

    @BeforeEach
    fun setup() {
        token = gerarToken()
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply<DefaultMockMvcBuilder?>(
            SecurityMockMvcConfigurers.springSecurity()
        ).build()
    }

    @Test
    fun `deve retornar codigo 200 quando chamar topicos com token`() {
        mockMvc.get(RECURSO) {
            headers {
                token?.let { this.setBearerAuth(it) }
            }
        }.andExpect {
            status { is2xxSuccessful() }
        }
    }

    @Test
    fun `deve retornar codigo 400 quando chamar topicos sem token`(){
        mockMvc.get(RECURSO).andExpect {
            status { is4xxClientError() }
        }
    }

    fun gerarToken(): String? {
        val authorities = mutableListOf(Role(2, "SOMENTE_LEITURA"))
        return jwtUtils.generateToken("joao@email.com", authorities)
    }


}