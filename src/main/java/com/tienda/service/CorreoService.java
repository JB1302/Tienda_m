/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoHtml(String para, String asunto, String contenido) 
            throws MessagingException {
        
        // Crea un nuevo mensaje MIME (permite enviar correos con contenido rico, adjuntos, etc.)
        MimeMessage mensaje = mailSender.createMimeMessage();
        // Ayuda a armar el mensaje de forma sencilla (maneja texto plano, HTML, adjuntos)
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

        //Destinario
        helper.setTo(para);
        //Asunto
        helper.setSubject(asunto);
        //Texto HTML
        helper.setText(contenido, true);
        //Enviar Correo
        mailSender.send(mensaje);
    }

}
