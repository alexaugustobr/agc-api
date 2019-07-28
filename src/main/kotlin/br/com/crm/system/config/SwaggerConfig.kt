package br.com.crm.system.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Parameter
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun apiV2Home(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .host(url)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                .paths(regex("/api/.*")).build()
                .apiInfo(apiInfo())
                .groupName("API-V1-Home(pública)")
                .globalResponseMessage(RequestMethod.GET, mutableListOf<ResponseMessage>(
                        ResponseMessageBuilder()
                                .code(404)
                                .message("Não foram encontrados resultados para sua busca.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(204)
                                .message("Resposta sem conteudo.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(401)
                                .message("Token inválido.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(403)
                                .message("Falha de autenticação.")
                                .build()
                ))
                .globalResponseMessage(RequestMethod.POST, mutableListOf<ResponseMessage>(
                        ResponseMessageBuilder()
                                .code(201)
                                .message("A API retorna uma mensagem de sucesso e o id do objeto cadastrado.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(200)
                                .message("A API retorna uma mensagem de sucesso.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(400)
                                .message("A API retorna a causa do erro junto com o campo e o objeto incorretos.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(401)
                                .message("Token inválido.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(403)
                                .message("Falha de autenticação.")
                                .build()
                ))
    }

    @Bean
    fun apiV2Admin(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .host(url)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                .paths(regex("/api/v1/backoffice/.*")).build()
                .apiInfo(apiInfo())
                .groupName("API-V1-Backoffice")
                .globalResponseMessage(RequestMethod.GET, mutableListOf<ResponseMessage>(
                        ResponseMessageBuilder()
                                .code(404)
                                .message("Não foram encontrados resultados para sua busca.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(204)
                                .message("Resposta sem conteudo.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(401)
                                .message("Token inválido.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(403)
                                .message("Falha de autenticação.")
                                .build()
                ))
                .globalResponseMessage(RequestMethod.POST, mutableListOf<ResponseMessage>(
                        ResponseMessageBuilder()
                                .code(201)
                                .message("A API retorna uma mensagem de sucesso e o id do objeto cadastrado.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(200)
                                .message("A API retorna uma mensagem de sucesso.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(400)
                                .message("A API retorna a causa do erro junto com o campo e o objeto incorretos.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(401)
                                .message("Token inválido.")
                                .build(),
                        ResponseMessageBuilder()
                                .code(403)
                                .message("Falha de autenticação.")
                                .build()
                ))
                .globalOperationParameters(mutableListOf<Parameter>(
                        ParameterBuilder()
                                .name("Authorization")
                                .parameterType("header")
                                .description("Token de acesso mais prefixo Bearer.\n Exemplo: \nAuthorization: Bearer cn389ncdfdsfsdfdsfsdfsoiwuencr")
                                .modelRef(ModelRef("string"))
                                .required(true)
                                .build()
                ))
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("CRM API")
                .description("Documentação da API")
                .version("1.0")
                .build()
    }

    companion object {

        private val url = "homol-agenciacreature-crm-api.herokuapp.com"
    }


}