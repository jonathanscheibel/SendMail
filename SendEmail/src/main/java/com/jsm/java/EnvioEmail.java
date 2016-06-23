package com.jsm.java;

import com.jsm.emails.EnvioCommonsMail;
import com.jsm.emails.EnvioJavaMail;

/**
 * Fontes:
 * http://www.itcuties.com/java/javamail-send-email/
 * http://www.devmedia.com.br/utilizando-a-api-commons-email-para-enviar-e-mails/3306
 * http://stackoverflow.com/questions/1783710/sending-an-email-using-commons-email-to-gmail
 * http://www.devmedia.com.br/enviando-e-mail-com-anexo/1635
 * https://commons.apache.org/proper/commons-email/userguide.html
 * https://github.com/stephenc/simple-java-mail
 */
public class EnvioEmail {

	public static void main(String[] args)  {		
		
		//Usando o JavaMail
		EnvioJavaMail.sendEmailJavaMailSimples();
		EnvioJavaMail.sendEmailJavaMailAnexo();
		
		//Usando o CommonsMail
		EnvioCommonsMail.sendEmailCommonsMailSimples();
		EnvioCommonsMail.sendEmailCommonsMailAnexo();		
	}
	
}



