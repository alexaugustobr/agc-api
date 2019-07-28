package br.com.crm.system.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

abstract class AbstractModelDTO (

        var id: UUID? = null,

        var dataCriacao : Date = Date(),

        var dataExclusao : Date? = null,

        var excluido : Boolean = false

) : DataTransferObject() {

    @JsonProperty
    fun getType()  = this.javaClass.simpleName

}