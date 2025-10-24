package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "pessoas",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_pessoas_cpf_cnpj", columnNames = "cpf_cnpj")
        }
)
@Data
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "nome_completo", nullable = false, length = 200)
    private String nomeCompleto;

    // CPF (11) ou CNPJ (14). Deixo 20 para folga (hífens/pontos, se algum dia usar formatado).
    @NotBlank
    @Size(max = 20)
    @Column(name = "cpf_cnpj", nullable = false, length = 20, unique = true)
    private String cpfCnpj;

    // CTPS numérico; sem 'length' (não se aplica a Long)
    @Column(name = "numero_ctps")
    private Long numeroCtps;

    // Se quiser opcional, troque para nullable = true e remova @NotNull
    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false, length = 15)
    private TipoPessoa tipoPessoa;

    /** Construtor JPA */


    public Pessoa(String nomeCompleto,
                  String cpfCnpj,
                  Long numeroCtps,
                  LocalDate dataNascimento,
                  TipoPessoa tipoPessoa) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.numeroCtps = numeroCtps;
        this.dataNascimento = dataNascimento;
        this.tipoPessoa = tipoPessoa;
    }


}
