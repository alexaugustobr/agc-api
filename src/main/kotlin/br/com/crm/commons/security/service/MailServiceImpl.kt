package br.com.crm.commons.security.service

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.HtmlEmail
import org.apache.commons.mail.SimpleEmail
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MailServiceImpl : MailService {

    @Value("mail.pwd")
    val YOUR_PASSWORD = ""
    val YOUR_EMAIL = "handcode@amazestudio.com.br"
    val MAIL_HOST = "br362.hostgator.com.br"
    val MAIL_SENDER = "handcode@amazestudio.com.br"

    override fun enviarEmail(destinatario: String, assunto: String, corpo: String) {
        try {
           Thread{
               HtmlEmail().apply {
                   hostName = MAIL_HOST
                   setSmtpPort(587)
                   setAuthenticator(DefaultAuthenticator(YOUR_EMAIL, YOUR_PASSWORD))
                   isSSLOnConnect = true
                   setFrom(MAIL_SENDER)
                   subject = assunto
                   setHtmlMsg(corpo.trimIndent())
                   addTo(destinatario)
               }.send()
           }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun enviarEmail(destinatarios: MutableList<String>, assunto: String, corpo: String) {
        for (destinatario in destinatarios) {
            try {
                SimpleEmail().apply {
                    hostName = MAIL_HOST
                    setSmtpPort(587)
                    setAuthenticator(DefaultAuthenticator(YOUR_EMAIL, YOUR_PASSWORD))
                    isSSLOnConnect = true
                    setFrom(MAIL_SENDER)
                    subject = assunto
                    setMsg(corpo.trimIndent())
                    addTo(destinatario)
                }.send()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

