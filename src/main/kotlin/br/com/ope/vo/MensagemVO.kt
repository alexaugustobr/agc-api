package br.com.ope.vo

data class MensagemVO(val conteudo : String = "",
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