package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Notification;
import br.com.fajbio.assistenciatecnica.domain.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    @Transactional
    protected Notification salvar(Notification notification) {
        return repository.save(notification);
    }

    public Notification cadastrar(Notification notification) {
        return salvar(notification);
    }
}
