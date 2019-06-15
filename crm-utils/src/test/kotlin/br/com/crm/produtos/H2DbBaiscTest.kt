package br.com.crm.produtos

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import javax.transaction.Transactional

@RunWith(SpringJUnit4ClassRunner::class)
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
//@PropertySource("src/test/resources/application-pgtest.properties")
@Ignore
class H2DbBaiscTest {

    @Test
    fun cadastrar_duas_disciplinas_e_bucar_as_duas_de_uma_vez() {

    }


}