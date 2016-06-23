package com.jsm.emails;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvioJavaMail {

	private static final String CONTEUDO_TEXTO_EMAIL = "EQUIPE WRJD";

	private static Message createMessage() {
		// Criando todas as proriedades necessárias
		Properties connectionProperties = new Properties();
		connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
		connectionProperties.put("mail.smtp.auth", "true");
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		connectionProperties.put("mail.smtp.socketFactory.port", "465");
		connectionProperties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		connectionProperties.put("mail.smtp.port", "465");

		// Criando a sessão com autenticação
		System.out.println("Criando a sessão autenticada...");
		Session session = Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"jonathansmorais@gmail.com",
								Informacoes.SENHA_CONTA);
					}
				});

		System.out.println("Conectado na conta do gmail!");
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("jonathan.tjq@gmail.com", "Jonathan Scheibel"));	
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("jonathan.tjq@gmail.com"));
			message.setSubject("Exemplo JavaMail");
			message.setText(CONTEUDO_TEXTO_EMAIL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * Rotina de email simples
	 */
	public static void sendEmailJavaMailSimples() {
		Message mensagem = createMessage();
		try {
			System.out.println("Enviando email!");
			Transport.send(mensagem);
			System.out.println("Email enviado!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rotina de email usando anexo
	 */
	public static void sendEmailJavaMailAnexo() {
		Message mensagem = createMessage();

		MimeBodyPart mbParte1 = new MimeBodyPart();
		try {
			mbParte1.setText(CONTEUDO_TEXTO_EMAIL);
		} catch (MessagingException e2) {
			e2.printStackTrace();
		}

		String filename = Informacoes.ARQUIVO_ANEXO;
		MimeBodyPart mbParte2 = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(filename);
		try {
			mbParte2.setDataHandler(new DataHandler(fds));
			mbParte2.setFileName(fds.getName());
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		Multipart mp = new MimeMultipart();
		try {
			mp.addBodyPart(mbParte1);
			mp.addBodyPart(mbParte2);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		try {
			mensagem.setContent(mp);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}

		try {
			System.out.println("Enviando email com anexo!");
			Transport.send(mensagem);
			System.out.println("Email enviado!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
