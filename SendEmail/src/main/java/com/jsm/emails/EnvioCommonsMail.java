package com.jsm.emails;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;


/**
 *  @author Jonathan
    SimpleEmail 	– Esta classe é usada para enviar e-mails simples.
    MultiPartEmail 	- Esta classe é usada emitir mensagens multipart. Isto permite uma mensagem de texto com anexos.
    EmailAttachment – Esta classe é usada para se criar os anexos do email. 
    HtmlEmail 		– Esta classe envia emails formatados como HTML.    
 */
public class EnvioCommonsMail {
	
	private static final String CONTEUDO_TEXTO_EMAIL = "EQUIPE WRJD";
	
	/**
	 * Criação do email simples
	 * @return
	 * @throws EmailException
	 */
	public static SimpleEmail createEmailSimples() throws EmailException{
		SimpleEmail email 	= new SimpleEmail();
		String authuser = "jonathansmorais@gmail.com";
		String authpwd 	= Informacoes.SENHA_CONTA;
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.getMailSession().getProperties().put("mail.smtps.auth", "true");
		email.getMailSession().getProperties().put("mail.debug", "true");
		email.getMailSession().getProperties().put("mail.smtps.port", "465");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "465");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
		email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
		email.setFrom("jonathan.tjq@gmail.com", "Jonathan Scheibel");
		email.addTo("jonathan.tjq@gmail.com");
		email.setSubject("Exemplo CommonsMail");
		email.setMsg(CONTEUDO_TEXTO_EMAIL + " - Email sem anexo");					
		return email;
	}
	
	/**
	 * Envio do email simples
	 */
	public static void sendEmailCommonsMailSimples() {				
		try {
			SimpleEmail mensagem = createEmailSimples();
			System.out.println("Enviando email simples...");
			mensagem.send();
			System.out.println("Email enviado!");
		} catch (EmailException e1) {
			e1.printStackTrace();
		}		
	}
	
	/**
	 * Criação do email anexo
	 * @return
	 * @throws EmailException
	 */
	public static MultiPartEmail createEmailAnexo() throws EmailException{
		MultiPartEmail email = new MultiPartEmail(); //
		String authuser = "jonathansmorais@gmail.com";
		String authpwd 	= Informacoes.SENHA_CONTA;
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.getMailSession().getProperties().put("mail.smtps.auth", "true");
		email.getMailSession().getProperties().put("mail.debug", "true");
		email.getMailSession().getProperties().put("mail.smtps.port", "465");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "465");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
		email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
		email.setFrom("jonathan.tjq@gmail.com", "Jonathan Scheibel");
		email.addTo("jonathan.tjq@gmail.com");
		email.setSubject("Exemplo CommonsMail");				
		email.setMsg(CONTEUDO_TEXTO_EMAIL + " - Email com anexo");
		return email;
	}
	
	/**
	 * Envio do email com anexo
	 */
	public static void sendEmailCommonsMailAnexo(){
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(Informacoes.ARQUIVO_ANEXO);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Listagem de usuários");
		attachment.setName("Lista");
		
		try {
			MultiPartEmail mensagem = createEmailAnexo();	
			mensagem.attach(attachment);
			System.out.println("Enviando email com anexo...");
			mensagem.send();
			System.out.println("Email enviado!");
		} catch (EmailException e1) {
			e1.printStackTrace();
		}		
	
	}
}
