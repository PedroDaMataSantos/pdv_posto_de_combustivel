package com.br.pdvpostocombustivel.config;

import com.br.pdvpostocombustivel.domain.entity.Acesso;
import com.br.pdvpostocombustivel.domain.repository.AcessoRepository;
import com.br.pdvpostocombustivel.enums.TipoAcesso;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private final AcessoRepository acessoRepository;

    public AdminInitializer(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    @PostConstruct
    public void criarAdminPadrao() {
        final String usuario = "dev";
        final String senha = "123"; // DEV ONLY

        // verifica se já existe usuário 'admin'
        var existente = acessoRepository.findByusuario(usuario);
        if (existente.isPresent()) {
            System.out.println("🔸 Usuário ADMIN padrão já existe.");
            return;
        }

        // cria Acesso (atenção aos nomes dos campos do seu entity)
        Acesso admin = new Acesso();
        admin.setUsuario(usuario);
        admin.setSenha(senha);

        admin.setPerfil(TipoAcesso.ADMIN);

        acessoRepository.save(admin);
        System.out.println("✅ Usuário ADMIN criado (usuario: dev / senha: 123) — remova após uso.");
    }
}
