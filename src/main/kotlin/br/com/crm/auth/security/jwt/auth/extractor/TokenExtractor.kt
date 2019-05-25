package br.com.crm.auth.security.jwt.auth.extractor

interface TokenExtractor {
    fun extract(payload: String): String

    fun extract(payload: String, token: String): String  // TODO quando os projetos implementarem o JWT, esse metodo será excluido
}
