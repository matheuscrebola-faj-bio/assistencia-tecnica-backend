package br.com.fajbio.assistenciatecnica.infra.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "spring.mail.enabled", havingValue = "true", matchIfMissing = false)
public class MailService {
//    private final JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String smtpUser; // para referência/logs se quiser
//
//    @Value("${mail.from:}")
//    private String defaultFrom; // pode ser vazio; se vazio, usa o próprio username
//
//    public void enviarComAnexo(String para,
//                               String assunto,
//                               String corpoHtml,
//                               Path anexo) throws Exception {
//
//        if (anexo == null || !Files.exists(anexo)) {
//            throw new IllegalArgumentException("Anexo não encontrado: " + anexo);
//        }
//
//        var msg = mailSender.createMimeMessage();
//        var helper = new MimeMessageHelper(msg, true, StandardCharsets.UTF_8.name());
//
//        helper.setFrom((defaultFrom != null && !defaultFrom.isBlank()) ? defaultFrom : smtpUser);
//        helper.setTo(para);
//        helper.setSubject(assunto);
//        helper.setText(corpoHtml, true);
//
//        String fileName = anexo.getFileName().toString();
//        String contentType = Files.probeContentType(anexo);
//        if (contentType == null || contentType.isBlank()) {
//            contentType = "application/octet-stream";
//        }
//        helper.addAttachment(fileName, (InputStreamSource) anexo.toFile(), contentType);
//
//        mailSender.send(msg);
//    }
//
//    /**
//     * Versão opcional para texto puro.
//     */
//    public void enviarTextoPuro(String para, String assunto, String corpoTexto) throws Exception {
//        MimeMessage msg = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(
//                msg, false, StandardCharsets.UTF_8.name());
//
//        if (defaultFrom != null && !defaultFrom.isBlank()) {
//            helper.setFrom(new InternetAddress(defaultFrom));
//        } else {
//            helper.setFrom(new InternetAddress(smtpUser));
//        }
//
//        helper.setTo(para);
//        helper.setSubject(assunto);
//        helper.setText(corpoTexto, false);
//        mailSender.send(msg);
//    }
}
