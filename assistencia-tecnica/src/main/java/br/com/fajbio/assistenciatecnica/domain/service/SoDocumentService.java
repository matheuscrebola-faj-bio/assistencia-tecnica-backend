package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import br.com.fajbio.assistenciatecnica.domain.model.SoDocument;
import br.com.fajbio.assistenciatecnica.domain.repository.SoDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoDocumentService {
    private final SoDocumentRepository repository;

    public void preencherDocumento(SoDocument document, ServiceOrderReq req) {
    }
}
