package br.com.crm.h2

import br.com.crm.commons.usuario.dto.UsuarioDTO
import br.com.crm.system.constantes.URL
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@RunWith(SpringJUnit4ClassRunner::class)
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UsuarioBackofficeAPITest : H2DbBasicTest() {

    val url = URL.API_USUARIO_BACKOFFICE

    @Test
    fun cadastrarBuscarEListar() {


        val dto = UsuarioDTO(
                nome = "alex",
                email = "alex@email.com.br",
                senha = "12345678"
        )

        val mvcPost = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)
                .content(this.json(dto)))
                .andExpect(status().is2xxSuccessful).andReturn()





        val dtoSalvo = this.toObject(mvcPost.response.contentAsString!!, UsuarioDTO::class.java)!!

        val mvcGetLista = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)).andExpect(status().is2xxSuccessful).andReturn()

        val mvcGetId = mockMvc.perform(MockMvcRequestBuilders.get(url + "/${dtoSalvo.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)).andExpect(status().is2xxSuccessful).andReturn()

    }

}