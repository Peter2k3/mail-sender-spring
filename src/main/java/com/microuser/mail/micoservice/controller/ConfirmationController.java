package com.microuser.mail.micoservice.controller;

import com.microuser.mail.entity.UserVerificationDTO;
import com.microuser.mail.micoservice.MailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/confirm-mail")
public class ConfirmationController {

    private final MailService mailService;

    public ConfirmationController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        mailService.sendSimpleEmail(to, subject, body);
        return "Email enviado con éxito a " + to;
    }

    @PostMapping("/send-html")
    public String sendHtmlMail(@RequestBody UserVerificationDTO userVerificationDTO) {
        String htmlContent = getHtmlTemplate(userVerificationDTO);
        String to = userVerificationDTO.getUser().getEmail();
        String subject = "Verificación de correo - UPCAuth";

        System.out.println(to);
        System.out.println(subject);
        try {
            mailService.sendHTMLemail(to, subject, htmlContent);
            System.out.println("se envio");
            return "Email enviado con éxito a " + to;
            
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("no se envio");

            return "Error enviando el email a " + to;
        }
    }

    private String getHtmlTemplate(UserVerificationDTO userVerificationDTO) {
        return """
            <html>
                <body style="background-color: #f7f7f7; color: #333333; font-family: Arial, sans-serif; padding: 0; margin: 0;">
                    <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); border: 1px solid #e0e0e0;">
                        
                        <header style="text-align: center; padding: 20px 0;">
                            <h1 style="color: #0078D4; font-size: 24px; margin: 0;">Bienvenido a UPCAuth</h1>
                            <p style="color: #555555; font-size: 14px;">Verificación de cuenta</p>
                        </header>

                        <section style="background-color: #f4f4f4; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
                            <p style="font-size: 16px; line-height: 1.5; color: #333333;">
                                ¡Hola!
                            </p>
                            <p style="font-size: 16px; line-height: 1.5; color: #333333;">
                                Gracias por registrarte en <strong style="color: #0078D4;">UPCAuth</strong>. Para completar tu verificación de correo, utiliza el siguiente código:
                            </p>
                            <div style="text-align: center; font-size: 30px; font-weight: bold; color: #0078D4; padding: 20px; background-color: #e9f5ff; border-radius: 8px; margin: 20px 0;">
                                %d
                            </div>
                        </section>

                        <section style="background-color: #f4f4f4; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
                            <p style="font-size: 16px; line-height: 1.5; color: #333333;">Código enviado al correo:</p>
                            <div style="text-align: center; font-size: 18px; font-weight: bold; color: #333333; margin: 10px 0;">
                                %s
                            </div>
                        </section>

                        <p style="font-size: 14px; color: #888888; text-align: center;">
                            Si no solicitaste esta verificación, puedes ignorar este mensaje.
                        </p>

                        <footer style="text-align: center; padding-top: 20px; margin-top: 20px; border-top: 1px solid #e0e0e0;">
                            <p style="font-size: 14px; color: #555555;">Saludos,<br>El equipo de <span style="color: #0078D4;">UPCAuth</span></p>
                            <a href="https://upcauth.com" style="color: #0078D4; text-decoration: none; font-size: 14px; font-weight: bold; display: inline-block; margin-top: 10px;">Visita nuestro sitio web</a>
                        </footer>

                    </div>
                </body>
            </html>
            """
                .formatted(userVerificationDTO.getCode(), userVerificationDTO.getUser().getEmail());
    }




}
