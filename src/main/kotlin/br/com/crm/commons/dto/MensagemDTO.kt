package br.com.crm.commons.dto

data class MensagemDTO(val conteudo : String = "",
                       val titulo : String = "",
                       val tipo : TipoMensagem = TipoMensagem.info,
                       val linkNome : String = "",
                       val linkURL : String = "") {

    enum class TipoMensagem {
        success,
        info,
        warning,
        danger
    }

}