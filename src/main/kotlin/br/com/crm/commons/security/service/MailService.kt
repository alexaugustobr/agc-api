package br.com.crm.commons.security.service

interface MailService {

    fun enviarEmail(destinatarios: MutableList<String>, assunto: String, corpo: String)

    fun enviarEmail(destinatario: String, assunto: String, corpo: String)

}