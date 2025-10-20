package br.com.fajbio.assistenciatecnica.infra.email;

public class EmailBodies {
    public static String instrucoesEnvioHtml() {
        return """
        <div style="font-family: Arial, sans-serif; line-height:1.5">
          <p><strong>Enviar o equipamento</strong> para o endereço abaixo junto com o formulário preenchido:</p>
          <p>
            <strong>A/C:</strong> Assistência Técnica CTE – Bioengenharia<br/>
            <strong>End:</strong> Av. Dr. Dante Pazzanese, 500<br/>
            <strong>CEP:</strong> 04012-180 São Paulo / SP
          </p>
          <p><strong>Horário de Entregas e Retiradas:</strong><br/>
             <em>Segunda a Sexta-feira</em><br/>
             Manhã: 08:00h às 11:00h<br/>
             Tarde: 13:00h às 16:00h
          </p>
          <p>O equipamento deve ser higienizado, protegido e bem embalado.</p>
          <p><strong>OBS:</strong> Informamos que os aparelhos MCA 2000 com número de série abaixo de MCA1267
          podem ser considerados inaptos para uso, essa versão não é mais fabricada e não temos
          mais como realizar a substituição / reposição de componentes e peças.</p>
        </div>
        """;
    }
}
