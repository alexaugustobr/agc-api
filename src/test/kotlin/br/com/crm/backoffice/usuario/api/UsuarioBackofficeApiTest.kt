package br.com.crm.backoffice.usuario.api

import br.com.crm.commons.usuario.dto.UsuarioDTO
import br.com.crm.h2.H2DbBasicTest
import br.com.crm.system.constantes.URL
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
@ContextConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UsuarioBackofficeApiTest : H2DbBasicTest() {

    val url = URL.API_USUARIO_BACKOFFICE

    @Test
    fun cadastrar() {

        val usuarioDTONovo = UsuarioDTO(
                nome = "alex",
                email = "alex@email.com.br",
                senha = "12345678"
        )

        val mvcPost = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)
                .content(this.json(usuarioDTONovo)))
                .andExpect(status().is2xxSuccessful).andReturn()

        val usuarioDTOSalvo = toObject(mvcPost, UsuarioDTO::class.java)

        Assert.assertEquals(usuarioDTOSalvo.email, usuarioDTONovo.email)
        Assert.assertNull(usuarioDTOSalvo.senha)
        Assert.assertEquals(usuarioDTOSalvo.nome, usuarioDTONovo.nome)

    }

    @Test
    fun atualizar() {

        var dto = UsuarioDTO(
                nome = "alex",
                email = "alex@email.com.br",
                senha = "12345678"
        )

        var result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)
                .content(this.json(dto)))
                .andExpect(status().is2xxSuccessful).andReturn()

        dto = toObject(result, UsuarioDTO::class.java)

        Assert.assertEquals(dto.email, dto.email)
        Assert.assertNull(dto.senha)
        Assert.assertEquals(dto.nome, dto.nome)

        val newName = "Alexey"
        val newEmail = "alexey@gmail.com"

        dto.nome = newName
        dto.email = newEmail

        val id = dto.id

        result = mockMvc.perform(MockMvcRequestBuilders.put("$url/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)
                .content(this.json(dto)))
                .andExpect(status().is2xxSuccessful).andReturn()

        dto = toObject(result, UsuarioDTO::class.java)

        Assert.assertEquals(newName, dto.nome)
        Assert.assertEquals(newEmail, dto.email)


    }

    @Test
    fun listar() {

        val result = this.mockMvc
                .perform(get(url).headers(authenticationHeaders))
                .andDo(print())
                .andExpect(status().isOk)
                .andReturn()

        val usuarioList = this.toList(result, UsuarioDTO::class.java)

        Assert.assertTrue(usuarioList.contains(adminBackoffice))

    }

    @Test
    fun buscarUm() {

        var dto = UsuarioDTO(
                nome = "alex",
                email = "alex@email.com.br",
                senha = "12345678"
        )

        var result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)
                .content(this.json(dto)))
                .andExpect(status().is2xxSuccessful).andReturn()

        dto = toObject(result, UsuarioDTO::class.java)

        val id = dto.id

        result = mockMvc.perform(MockMvcRequestBuilders.get("$url/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(authenticationHeaders)
                .content(this.json(dto)))
                .andExpect(status().is2xxSuccessful).andReturn()

        val dtoResultadoBusca = toObject(result, UsuarioDTO::class.java)



        Assert.assertEquals(dto.email, dtoResultadoBusca.email)
        Assert.assertNull(dtoResultadoBusca.senha)
        Assert.assertEquals(dto.nome, dtoResultadoBusca.nome)

    }
}