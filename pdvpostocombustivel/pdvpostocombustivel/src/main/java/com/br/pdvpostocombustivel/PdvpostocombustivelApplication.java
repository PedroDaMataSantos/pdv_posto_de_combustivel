package com.br.pdvpostocombustivel;

import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.util.Date;

@SpringBootApplication
public class PdvpostocombustivelApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PdvpostocombustivelApplication.class, args);

		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNomeCompleto("Pedro");
		pessoa1.setCpfCnpj("70058865188");
		pessoa1.setNumeroCtps(1223L);

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNomeCompleto("Lucca");
		pessoa2.setCpfCnpj("70058865188");
		pessoa2.setNumeroCtps(1223L);

		Pessoa pessoa3 = new Pessoa();
		pessoa3.setNomeCompleto("Moises");
		pessoa3.setCpfCnpj("70058865188");
		pessoa3.setNumeroCtps(1223L);

		System.out.println("Nome Completo:" + pessoa1.getNomeCompleto());
		System.out.println("CPF/CNPJ:" + pessoa1.getCpfCnpj());
		System.out.println("Numero CTPs:" + pessoa1.getNumeroCtps());

		System.out.println("-----------------------------------------------");

		System.out.println("Nome Completo:" + pessoa2.getNomeCompleto());
		System.out.println("CPF/CNPJ:" + pessoa2.getCpfCnpj());
		System.out.println("Numero CTPs:" + pessoa2.getNumeroCtps());

		System.out.println("-----------------------------------------------");

		System.out.println("Nome Completo:" + pessoa2.getNomeCompleto());
		System.out.println("CPF/CNPJ:" + pessoa2.getCpfCnpj());
		System.out.println("Numero CTPs:" + pessoa2.getNumeroCtps());
	}

}
