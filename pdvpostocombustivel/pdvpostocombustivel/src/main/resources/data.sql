-- Inserindo alguns registros iniciais
INSERT INTO pessoas (id, nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa)
VALUES ('0002', 'Maria da Silva', '12345678901', 12345, '1990-05-10', 'FISICA')
ON CONFLICT (cpf_cnpj) DO NOTHING;

INSERT INTO pessoas (id, nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa)
VALUES ('0001', 'João Pereira', '98765432100', 54321, '1985-11-20', 'FISICA')
ON CONFLICT (cpf_cnpj) DO NOTHING;