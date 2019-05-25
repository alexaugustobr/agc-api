package br.com.crm.auth.service

interface MailService {

    fun enviarEmail(destinatarios: MutableList<String>, assunto: String, corpo: String)

    fun enviarEmail(destinatario: String, assunto: String, corpo: String)

}