-- ============================================
-- DADOS MOCKADOS - SISTEMA DE ASSISTÊNCIA TÉCNICA
-- ============================================

-- 1. ENDEREÇOS
INSERT INTO assistencia_tecnica.tb_addresses (cep, rua, numero, complemento, bairro, cidade, uf, pais) VALUES
                                                                                                           ('01310-100', 'Avenida Paulista', '1578', 'Conjunto 45', 'Bela Vista', 'São Paulo', 'SP', 'Brasil'),
                                                                                                           ('22250-040', 'Avenida Atlântica', '1702', 'Cobertura', 'Copacabana', 'Rio de Janeiro', 'RJ', 'Brasil'),
                                                                                                           ('30130-010', 'Avenida Afonso Pena', '867', 'Sala 301', 'Centro', 'Belo Horizonte', 'MG', 'Brasil'),
                                                                                                           ('80010-000', 'Rua XV de Novembro', '500', NULL, 'Centro', 'Curitiba', 'PR', 'Brasil'),
                                                                                                           ('40020-000', 'Avenida Sete de Setembro', '1234', 'Loja 2', 'Centro', 'Salvador', 'BA', 'Brasil'),
                                                                                                           ('90010-150', 'Rua dos Andradas', '1234', 'Andar 5', 'Centro Histórico', 'Porto Alegre', 'RS', 'Brasil'),
                                                                                                           ('60060-000', 'Avenida Beira Mar', '3980', NULL, 'Meireles', 'Fortaleza', 'CE', 'Brasil'),
                                                                                                           ('88010-400', 'Rua Felipe Schmidt', '515', 'Sala 1002', 'Centro', 'Florianópolis', 'SC', 'Brasil');

-- 2. CLIENTES
INSERT INTO assistencia_tecnica.tb_customers (documento, nome_legal, ativo) VALUES
                                                                                ('12.345.678/0001-90', 'Hospital São Lucas LTDA', true),
                                                                                ('98.765.432/0001-10', 'Clínica Vida e Saúde S.A.', true),
                                                                                ('11.222.333/0001-44', 'Centro Médico Esperança', true),
                                                                                ('55.666.777/0001-88', 'Hospital Regional Norte', true),
                                                                                ('33.444.555/0001-22', 'Clínica Oftalmológica Visão Clara', true),
                                                                                ('77.888.999/0001-66', 'Laboratório Diagnóstico Plus', false),
                                                                                ('22.333.444/0001-55', 'Hospital Universitário Central', true);

-- 3. ENDEREÇOS DOS CLIENTES
INSERT INTO assistencia_tecnica.tb_customer_addresses (customer_id, address_id, tipo) VALUES
                                                                                          (1, 1, 'COMERCIAL'),
                                                                                          (2, 2, 'COMERCIAL'),
                                                                                          (3, 3, 'COMERCIAL'),
                                                                                          (4, 4, 'COMERCIAL'),
                                                                                          (5, 5, 'COMERCIAL'),
                                                                                          (6, 6, 'COMERCIAL'),
                                                                                          (7, 7, 'COMERCIAL');

-- 4. CONTATOS DOS CLIENTES
INSERT INTO assistencia_tecnica.tb_customer_contacts (customer_id, nome, telefone, email) VALUES
                                                                                              (1, 'Dr. Carlos Silva', '(11) 3456-7890', 'carlos.silva@saolucas.com.br'),
                                                                                              (1, 'Mariana Souza', '(11) 98765-4321', 'mariana.souza@saolucas.com.br'),
                                                                                              (2, 'Dra. Ana Costa', '(21) 2345-6789', 'ana.costa@vidasaude.com.br'),
                                                                                              (3, 'João Santos', '(31) 3234-5678', 'joao.santos@esperanca.com.br'),
                                                                                              (4, 'Fernanda Lima', '(41) 3345-6789', 'fernanda.lima@regionalnorte.com.br'),
                                                                                              (5, 'Dr. Ricardo Mendes', '(71) 3456-7890', 'ricardo.mendes@visaoclara.com.br'),
                                                                                              (7, 'Prof. Paulo Oliveira', '(85) 3567-8901', 'paulo.oliveira@universitario.edu.br');

-- 5. PERFIS/ROLES
INSERT INTO assistencia_tecnica.tb_roles (nome) VALUES
                                                    ('ADMIN'),
                                                    ('TECNICO'),
                                                    ('ATENDENTE'),
                                                    ('GERENTE'),
                                                    ('FINANCEIRO');

-- 6. USUÁRIOS
INSERT INTO assistencia_tecnica.tb_users (username, email, password_hash, ativo) VALUES
                                                                                     ('admin', 'admin@empresa.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true),
                                                                                     ('tecnico1', 'tecnico1@empresa.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true),
                                                                                     ('tecnico2', 'tecnico2@empresa.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true),
                                                                                     ('atendente1', 'atendente@empresa.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true),
                                                                                     ('gerente', 'gerente@empresa.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true),
                                                                                     ('financeiro', 'financeiro@empresa.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true);

-- 7. ASSOCIAÇÃO USUÁRIOS-PERFIS
INSERT INTO assistencia_tecnica.tb_user_roles (user_id, role_id) VALUES
                                                                     (1, 1), -- admin -> ADMIN
                                                                     (2, 2), -- tecnico1 -> TECNICO
                                                                     (3, 2), -- tecnico2 -> TECNICO
                                                                     (4, 3), -- atendente1 -> ATENDENTE
                                                                     (5, 4), -- gerente -> GERENTE
                                                                     (6, 5); -- financeiro -> FINANCEIRO

-- 8. TIPOS DE EQUIPAMENTO
INSERT INTO assistencia_tecnica.tb_equipment_types (nome) VALUES
                                                              ('Monitor de Coagulação'),
                                                              ('Kit Diagnóstico'),
                                                              ('Sistema Eletrocirúrgico'),
                                                              ('Tubos Traqueais'),
                                                              ('Válvula de Fala'),
                                                              ('Instrumentais Cirúrgicos'),
                                                              ('Sistema de Aquecimento');

-- 9. MODELOS DE EQUIPAMENTO
INSERT INTO assistencia_tecnica.tb_equipment_models (type_id, fabricante, modelo) VALUES
                                                                                      (1, 'BioMed Tech', 'CoagCheck Pro 3000'),
                                                                                      (1, 'MediCare', 'CoagMonitor Plus'),
                                                                                      (2, 'DiagnoSys', 'DiagKit Advanced'),
                                                                                      (3, 'ElectroSurg', 'ES-5000'),
                                                                                      (3, 'SurgTech', 'PowerSurg 300'),
                                                                                      (4, 'AirWay Medical', 'TraqTube Flex'),
                                                                                      (5, 'SpeechValve Co', 'VocaValve Premium'),
                                                                                      (6, 'Surgical Tools Inc', 'SurgiSet Complete'),
                                                                                      (7, 'ThermoMed', 'WarmSys 2000');

-- 10. EQUIPAMENTOS
INSERT INTO assistencia_tecnica.tb_equipments (customer_id, model_id, serial, data_ultima_garantia) VALUES
                                                                                                        (1, 1, 'CC3000-2023-001', '2025-12-31'),
                                                                                                        (1, 3, 'DKA-2023-045', '2024-06-30'),
                                                                                                        (2, 2, 'CMP-2022-156', '2024-03-15'),
                                                                                                        (3, 4, 'ES5K-2023-089', '2025-09-30'),
                                                                                                        (4, 5, 'PS300-2023-234', '2025-11-20'),
                                                                                                        (5, 6, 'TTF-2023-012', '2024-08-10'),
                                                                                                        (7, 8, 'SSC-2022-567', '2024-12-31'),
                                                                                                        (1, 9, 'WS2K-2023-099', '2026-01-15');

-- 11. COMPONENTES/PEÇAS
INSERT INTO assistencia_tecnica.tb_components (peca, preco, unidade) VALUES
                                                                         ('Sensor de Temperatura', 150.00, 1),
                                                                         ('Eletrodo Descartável', 25.50, 10),
                                                                         ('Cabo de Conexão USB', 45.00, 1),
                                                                         ('Placa Controladora Principal', 890.00, 1),
                                                                         ('Display LCD 7 polegadas', 320.00, 1),
                                                                         ('Fonte de Alimentação 12V', 180.00, 1),
                                                                         ('Bateria Recarregável', 95.00, 1),
                                                                         ('Kit de Calibração', 450.00, 1),
                                                                         ('Filtro HEPA', 65.00, 5),
                                                                         ('Transdutor de Pressão', 280.00, 1);

-- 12. COMPONENTES DOS EQUIPAMENTOS
INSERT INTO assistencia_tecnica.tb_equipment_components (equipment_id, component_id, quantidade) VALUES
                                                                                                     (1, 1, 2),
                                                                                                     (1, 4, 1),
                                                                                                     (1, 5, 1),
                                                                                                     (2, 3, 1),
                                                                                                     (2, 6, 1),
                                                                                                     (3, 1, 1),
                                                                                                     (3, 4, 1),
                                                                                                     (4, 4, 1),
                                                                                                     (4, 6, 1);

-- 13. SERVIÇOS
INSERT INTO assistencia_tecnica.tb_services (nome, preco_base) VALUES
                                                                   ('Calibração Completa', 350.00),
                                                                   ('Manutenção Preventiva', 280.00),
                                                                   ('Troca de Sensor', 420.00),
                                                                   ('Atualização de Software', 180.00),
                                                                   ('Reparo de Display', 550.00),
                                                                   ('Substituição de Placa', 980.00),
                                                                   ('Limpeza Técnica', 150.00),
                                                                   ('Teste de Funcionalidade', 200.00);

-- 14. STATUS DE ORDEM DE SERVIÇO
INSERT INTO assistencia_tecnica.tb_so_status (nome) VALUES
                                                        ('AGUARDANDO_RECEBIMENTO'),
                                                        ('RECEBIDO'),
                                                        ('TESTES_INICIAIS'),
                                                        ('VALIDAR_ORACAMENTO'),
                                                        ('RETORNO_ORCAMENTO'),
                                                        ('LIBERADO_REPARO'),
                                                        ('EXPEDICAO'),
                                                        ('FATURAR'),
                                                        ('AGUARDANDO_RETIRADA'),
                                                        ('FINALIZADO');

-- 15. CATÁLOGO DE CHECKLISTS
INSERT INTO assistencia_tecnica.tb_checklist_catalog (nome, descricao) VALUES
                                                                           ('Recebimento de Equipamento', 'Checklist para recebimento inicial de equipamentos'),
                                                                           ('Testes Iniciais', 'Checklist de testes funcionais iniciais'),
                                                                           ('Calibração', 'Checklist do processo de calibração'),
                                                                           ('Testes Finais', 'Checklist de validação antes da expedição');

-- 16. ITENS DO CATÁLOGO DE CHECKLISTS
INSERT INTO assistencia_tecnica.tb_checklist_catalog_items (checklist_catalog_id, tipo, etiqueta) VALUES
                                                                                                      (1, 'CHECKBOX', 'Equipamento recebido sem danos físicos'),
                                                                                                      (1, 'CHECKBOX', 'Lacre de segurança intacto'),
                                                                                                      (1, 'TEXT', 'Número de série conferido'),
                                                                                                      (1, 'CHECKBOX', 'Acessórios completos'),
                                                                                                      (2, 'CHECKBOX', 'Equipamento liga normalmente'),
                                                                                                      (2, 'TEXT', 'Resultado do teste de funcionalidade'),
                                                                                                      (2, 'CHECKBOX', 'Display funcionando'),
                                                                                                      (2, 'TEXT', 'Defeito relatado reproduzido'),
                                                                                                      (3, 'TEXT', 'Padrão de calibração utilizado'),
                                                                                                      (3, 'NUMBER', 'Temperatura ambiente (°C)'),
                                                                                                      (3, 'NUMBER', 'Umidade relativa (%)'),
                                                                                                      (3, 'CHECKBOX', 'Valores dentro da tolerância'),
                                                                                                      (4, 'CHECKBOX', 'Todos os testes aprovados'),
                                                                                                      (4, 'CHECKBOX', 'Certificado de calibração emitido'),
                                                                                                      (4, 'TEXT', 'Observações finais');

-- 17. ORDENS DE SERVIÇO
INSERT INTO assistencia_tecnica.tb_service_orders
(customer_id, equipment_id, current_status, origem, product_line, requester_company_name,
 requester_contato, requester_email, requester_address, atendimento, assigned_to_user_id,
 criado_em, ativo, ultimo_valor) VALUES
                                     (1, 1, 'LIBERADO_REPARO', 'WEB_FORM', 'MONITOR_COAGULACAO', 'Hospital São Lucas LTDA',
                                      'Dr. Carlos Silva', 'carlos.silva@saolucas.com.br', 'Av. Paulista, 1578 - São Paulo/SP',
                                      'Equipamento apresenta erro no display', 2, '2024-10-15 09:30:00', true, 3),
                                     (2, 3, 'VALIDAR_ORACAMENTO', 'INTERNO', 'MONITOR_COAGULACAO', 'Clínica Vida e Saúde S.A.',
                                      'Dra. Ana Costa', 'ana.costa@vidasaude.com.br', 'Av. Atlântica, 1702 - Rio de Janeiro/RJ',
                                      'Calibração periódica programada', 3, '2024-10-20 14:15:00', true, 2),
                                     (3, 4, 'TESTES_INICIAIS', 'WEB_FORM', 'ELETRO_SYSTEM', 'Centro Médico Esperança',
                                      'João Santos', 'joao.santos@esperanca.com.br', 'Av. Afonso Pena, 867 - Belo Horizonte/MG',
                                      'Equipamento não liga', 2, '2024-10-25 10:45:00', true, 1),
                                     (4, 5, 'FINALIZADO', 'INTERNO', 'ELETRO_SYSTEM', 'Hospital Regional Norte',
                                      'Fernanda Lima', 'fernanda.lima@regionalnorte.com.br', 'Rua XV de Novembro, 500 - Curitiba/PR',
                                      'Manutenção preventiva', 3, '2024-09-10 08:00:00', true, 5),
                                     (1, 2, 'RECEBIDO', 'WEB_FORM', 'KIT_DIAGNOSTICO', 'Hospital São Lucas LTDA',
                                      'Mariana Souza', 'mariana.souza@saolucas.com.br', 'Av. Paulista, 1578 - São Paulo/SP',
                                      'Software não atualiza', 2, '2024-10-28 16:20:00', true, 0);

-- 18. HISTÓRICO DE STATUS DAS ORDENS
INSERT INTO assistencia_tecnica.tb_so_status_history
(service_order_id, from_status_id, to_status_id, changed_by_user_id, atualizado_em) VALUES
                                                                                        (1, NULL, 1, 4, '2024-10-15 09:30:00'),
                                                                                        (1, 1, 2, 4, '2024-10-16 10:15:00'),
                                                                                        (1, 2, 3, 2, '2024-10-17 14:30:00'),
                                                                                        (1, 3, 6, 5, '2024-10-18 09:00:00'),
                                                                                        (2, NULL, 1, 4, '2024-10-20 14:15:00'),
                                                                                        (2, 1, 2, 4, '2024-10-21 11:00:00'),
                                                                                        (2, 2, 4, 3, '2024-10-22 15:45:00'),
                                                                                        (3, NULL, 1, 4, '2024-10-25 10:45:00'),
                                                                                        (3, 1, 2, 4, '2024-10-26 08:30:00'),
                                                                                        (3, 2, 3, 2, '2024-10-27 10:00:00'),
                                                                                        (4, NULL, 1, 4, '2024-09-10 08:00:00'),
                                                                                        (4, 1, 2, 4, '2024-09-11 09:15:00'),
                                                                                        (4, 2, 3, 3, '2024-09-12 13:20:00'),
                                                                                        (4, 3, 6, 5, '2024-09-13 10:30:00'),
                                                                                        (4, 6, 10, 3, '2024-09-20 16:00:00'),
                                                                                        (5, NULL, 1, 4, '2024-10-28 16:20:00'),
                                                                                        (5, 1, 2, 4, '2024-10-29 09:00:00');

-- 19. RECEBIMENTO (INTAKE)
INSERT INTO assistencia_tecnica.tb_so_intake
(service_order_id, data_chegada, lacre_intacto, observacoes) VALUES
                                                                 (1, '2024-10-16', true, 'Equipamento recebido em boas condições, display com falha confirmada'),
                                                                 (2, '2024-10-21', true, 'Recebido para calibração periódica'),
                                                                 (3, '2024-10-26', true, 'Equipamento não liga, possível problema na fonte'),
                                                                 (4, '2024-09-11', true, 'Manutenção preventiva programada'),
                                                                 (5, '2024-10-29', false, 'Lacre violado, equipamento apresenta sinais de abertura');

-- 20. TESTES INICIAIS
INSERT INTO assistencia_tecnica.tb_initial_tests
(service_order_id, tecnico_id, criado_em, aparelho, resultado, valores, ativo) VALUES
                                                                                   (1, 2, '2024-10-17 14:30:00', 'CoagCheck Pro 3000', 'Display com pixels mortos', 'Tensão: 12.1V, Corrente: 0.5A', true),
                                                                                   (2, 3, '2024-10-22 15:45:00', 'CoagMonitor Plus', 'Funcionamento normal', 'Todos parâmetros OK', true),
                                                                                   (3, 2, '2024-10-27 10:00:00', 'ES-5000', 'Fonte de alimentação defeituosa', 'Tensão saída: 0V', true),
                                                                                   (4, 3, '2024-09-12 13:20:00', 'PowerSurg 300', 'Funcionamento normal', 'Calibração necessária', true);

-- 21. ORDENS DE TRABALHO
INSERT INTO assistencia_tecnica.tb_work_orders
(service_order_id, tecnico_id, data_inicio, data_fim, observacoes) VALUES
                                                                       (1, 2, '2024-10-18 09:00:00', '2024-10-19 17:00:00', 'Substituição do display LCD realizada com sucesso'),
                                                                       (4, 3, '2024-09-13 10:30:00', '2024-09-15 16:00:00', 'Calibração completa e testes funcionais realizados'),
                                                                       (3, 2, '2024-10-28 08:00:00', NULL, 'Em andamento - aguardando peça de reposição');

-- 22. LOGS DE TRABALHO
INSERT INTO assistencia_tecnica.tb_work_logs (work_order_id, evento) VALUES
                                                                         (1, 'Iniciado diagnóstico do display'),
                                                                         (1, 'Display LCD removido'),
                                                                         (1, 'Novo display instalado'),
                                                                         (1, 'Testes funcionais realizados'),
                                                                         (1, 'Trabalho concluído'),
                                                                         (2, 'Calibração iniciada'),
                                                                         (2, 'Ajustes de temperatura realizados'),
                                                                         (2, 'Testes de precisão concluídos'),
                                                                         (2, 'Certificado emitido'),
                                                                         (3, 'Diagnóstico da fonte iniciado'),
                                                                         (3, 'Fonte defeituosa confirmada'),
                                                                         (3, 'Aguardando peça');

-- 23. ORÇAMENTOS
INSERT INTO assistencia_tecnica.tb_quotes
(service_order_id, quote_status, criado_em, created_by_user_id, validade, revision) VALUES
                                                                                        (2, 'CRIADA', '2024-10-23 10:00:00', 5, '2024-11-23', 0),
                                                                                        (3, 'CRIADA', '2024-10-28 14:30:00', 5, '2024-11-28', 0),
                                                                                        (4, 'ENCERRADA', '2024-09-13 11:00:00', 5, '2024-10-13', 0);

-- 24. ITENS DO ORÇAMENTO
INSERT INTO assistencia_tecnica.tb_quote_items (quote_id, service_id, descricao) VALUES
                                                                                     (1, 1, 'Calibração completa do monitor de coagulação'),
                                                                                     (1, 8, 'Testes de funcionalidade pós-calibração'),
                                                                                     (2, 6, 'Substituição da fonte de alimentação'),
                                                                                     (2, 8, 'Testes de funcionalidade'),
                                                                                     (3, 1, 'Calibração completa'),
                                                                                     (3, 2, 'Manutenção preventiva');

-- 25. EVENTOS DO ORÇAMENTO
INSERT INTO assistencia_tecnica.tb_quote_events (quote_id, tipo, data_hora, user_id) VALUES
                                                                                         (1, 'CRIACAO', '2024-10-23 10:00:00', 5),
                                                                                         (2, 'CRIACAO', '2024-10-28 14:30:00', 5),
                                                                                         (3, 'CRIACAO', '2024-09-13 11:00:00', 5);

-- 26. CALIBRAÇÕES
INSERT INTO assistencia_tecnica.tb_calibrations
(service_order_id, tecnico_id, data_calibracao, validade, referencia_certificado) VALUES
    (4, 3, '2024-09-14', '2025-09-14', 'CAL-2024-0934');

-- 27. PONTOS DE CALIBRAÇÃO
INSERT INTO assistencia_tecnica.tb_calibration_points
(calibration_id, grandeza, valor_nominal, valor_medido, incerteza) VALUES
                                                                       (1, 'Temperatura', 37.0, 37.05, 0.1),
                                                                       (1, 'Temperatura', 50.0, 50.02, 0.1),
                                                                       (1, 'Temperatura', 100.0, 99.98, 0.1),
                                                                       (1, 'Pressão', 100.0, 100.3, 0.5),
                                                                       (1, 'Pressão', 200.0, 199.8, 0.5);

-- 28. CHECKLISTS
INSERT INTO assistencia_tecnica.tb_checklists
(service_order_id, checklist_catalog_id, preenchido_por_user_id, preenchido_em) VALUES
                                                                                    (1, 1, 4, '2024-10-16 10:15:00'),
                                                                                    (1, 2, 2, '2024-10-17 14:30:00'),
                                                                                    (4, 1, 4, '2024-09-11 09:15:00'),
                                                                                    (4, 3, 3, '2024-09-14 10:00:00'),
                                                                                    (4, 4, 3, '2024-09-15 16:00:00');

-- 29. ITENS DOS CHECKLISTS
INSERT INTO assistencia_tecnica.tb_checklist_items (checklist_id, catalog_item_id, valor) VALUES
                                                                                              (1, 1, 'true'),
                                                                                              (1, 2, 'true'),
                                                                                              (1, 3, 'CC3000-2023-001'),
                                                                                              (1, 4, 'true'),
                                                                                              (2, 5, 'true'),
                                                                                              (2, 6, 'Display com pixels mortos confirmado'),
                                                                                              (2, 7, 'false'),
                                                                                              (2, 8, 'Sim, defeito reproduzido consistentemente'),
                                                                                              (3, 1, 'true'),
                                                                                              (3, 2, 'true'),
                                                                                              (3, 3, 'PS300-2023-234'),
                                                                                              (3, 4, 'true'),
                                                                                              (4, 9, 'Padrão RBC PT-100'),
                                                                                              (4, 10, '23.5'),
                                                                                              (4, 11, '55'),
                                                                                              (4, 12, 'true'),
                                                                                              (5, 13, 'true'),
                                                                                              (5, 14, 'true'),
                                                                                              (5, 15, 'Equipamento calibrado e testado com sucesso');

-- 30. ENCERRAMENTO
INSERT INTO assistencia_tecnica.tb_so_closeout
(service_order_id, data_conclusao, testes_finais_ok, observacoes) VALUES
    (4, '2024-09-20', true, 'Equipamento calibrado e testado. Funcionamento perfeito.');

-- 31. NOTAS FISCAIS
INSERT INTO assistencia_tecnica.tb_invoices
(service_order_id, numero, data_emissao, valor_total, impostos, invoice_status) VALUES
    (4, 'NF-2024-00234', '2024-09-20', 630.00, 113.40, 'EMITIDA');

-- 32. ITENS DA NOTA FISCAL
INSERT INTO assistencia_tecnica.tb_invoice_items
(invoice_id, quote_item_id, descricao, quantidade, preco_unitario) VALUES
                                                                       (1, 5, 'Calibração completa', 1, 350.00),
                                                                       (1, 6, 'Manutenção preventiva', 1, 280.00);

-- 33. EXPEDIÇÃO
INSERT INTO assistencia_tecnica.tb_shipments
(service_order_id, mode, transportadora, codigo_rastreio, data_envio) VALUES
    (4, 'CARRIER', 'Transportadora Rápida Ltda', 'BR123456789', '2024-09-21');

-- 34. EVENTOS DE EXPEDIÇÃO
INSERT INTO assistencia_tecnica.tb_shipment_events
(shipment_id, data_hora, shipment_status, localizacao) VALUES
                                                           (1, '2024-09-21 08:00:00', 'POSTADO', 'Centro de Distribuição - São Paulo/SP'),
                                                           (1, '2024-09-21 14:30:00', 'EM_TRANSITO', 'Em rota para Curitiba/PR'),
                                                           (1, '2024-09-22 09:15:00', 'CHEGOU_CIDADE_DESTINO', 'Centro de Distribuição - Curitiba/PR'),
                                                           (1, '2024-09-22 15:00:00', 'ENTREGUE', 'Hospital Regional Norte - Curitiba/PR');

-- 35. NOTIFICAÇÕES
INSERT INTO assistencia_tecnica.tb_notifications
(service_order_id, tipo, template, destinatario, enviado_em) VALUES
                                                                 (1, 'EMAIL', 'os_criada', 'carlos.silva@saolucas.com.br', '2024-10-15 09:35:00'),
                                                                 (1, 'EMAIL', 'os_recebida', 'carlos.silva@saolucas.com.br', '2024-10-16 10:20:00'),
                                                                 (2, 'EMAIL', 'os_criada', 'ana.costa@vidasaude.com.br', '2024-10-20 14:20:00'),
                                                                 (4, 'EMAIL', 'os_finalizada', 'fernanda.lima@regionalnorte.com.br', '2024-09-20 16:10:00'),
                                                                 (4, 'EMAIL', 'nf_emitida', 'fernanda.lima@regionalnorte.com.br', '2024-09-20 16:15:00');

-- 36. DOCUMENTOS DA OS
INSERT INTO assistencia_tecnica.tb_so_documents
(service_order_id, tipo_doc, nome, file_path, criado_em) VALUES
                                                             (1, 'FORMULARIO_DE_SERVICO', 'Formulário OS 001', '/docs/2024/10/form_os001.pdf', '2024-10-15 09:30:00'),
                                                             (4, 'CERTIFICADO_CALIBRACAO', 'Certificado CAL-2024-0934', '/docs/2024/09/cert_cal_0934.pdf', '2024-09-15 10:00:00'),
                                                             (4, 'LAUDO_TESTE_FINAL', 'Laudo Técnico Final OS 004', '/docs/2024/09/laudo_os004.pdf', '2024-09-15 16:00:00');

-- 37. LOGS DE ACESSO
INSERT INTO assistencia_tecnica.tb_access_logs (user_id, data_hora, metodo, endpoint) VALUES
                                                                                          (1, '2024-10-28 08:00:00', 'GET', '/api/dashboard'),
                                                                                          (4, '2024-10-28 08:15:00', 'POST', '/api/service-orders'),
                                                                                          (2, '2024-10-28 09:30:00', 'GET', '/api/service-orders/3'),
                                                                                          (2, '2024-10-28 10:00:00', 'PUT', '/api/service-orders/3/status'),
                                                                                          (5, '2024-10-28 11:00:00', 'POST', '/api/quotes'),
                                                                                          (1, '2024-10-28 14:00:00', 'GET', '/api/reports/monthly');

-- ============================================
-- FIM DOS DADOS MOCKADOS
-- ============================================

-- Verificação rápida dos dados inseridos
SELECT 'Endereços' as tabela, COUNT(*) as registros FROM assistencia_tecnica.tb_addresses
UNION ALL SELECT 'Clientes', COUNT(*) FROM assistencia_tecnica.tb_customers
UNION ALL SELECT 'Usuários', COUNT(*) FROM assistencia_tecnica.tb_users
UNION ALL SELECT 'Equipamentos', COUNT(*) FROM assistencia_tecnica.tb_equipments
UNION ALL SELECT 'Ordens de Serviço', COUNT(*) FROM assistencia_tecnica.tb_service_orders
UNION ALL SELECT 'Orçamentos', COUNT(*) FROM assistencia_tecnica.tb_quotes
UNION ALL SELECT 'Notas Fiscais', COUNT(*) FROM assistencia_tecnica.tb_invoices;