package br.com.fajbio.assistenciatecnica.infra.email;


import br.com.fajbio.assistenciatecnica.domain.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnvioDocumento {
//    private final DocumentService documentService;
//
//    @Autowired(required = false)
//    private MailService mailService;
//
//    public void executar() throws Exception {
//        // 1) Dados para preencher o DOCX (devem casar com ${chave} no template)
//        Map<String, String> vars = Map.of(
//                "empresa", "Clínica Exemplo",
//                "cnpj", "00.000.000/0001-00",
//                "contato", "Dra. Maria da Silva",
//                "descricao", "Assistência técnica referente ao equipamento X."
//        );
//
//        // 2) Gera PDF
//        Path pdf = documentService.gerarPdf(vars);
//
//        // 3) Corpo do email (HTML)
//        String corpoHtml = """
//            <p>Olá,</p>
//            <p>Segue em anexo o documento gerado automaticamente.</p>
//            <p>Atenciosamente,<br/>Equipe Assistência Técnica</p>
//        """;
//
//        // 4) Envia
//        mailService.enviarComAnexoPdf(
//                "destinatario@exemplo.com",
//                "Documento de Assistência Técnica",
//                corpoHtml,
//                pdf
//        );
//    }
}
