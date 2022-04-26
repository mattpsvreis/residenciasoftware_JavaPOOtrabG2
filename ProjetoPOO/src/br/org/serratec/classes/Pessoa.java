package br.org.serratec.classes;

import java.time.LocalDate;

public abstract class Pessoa implements Comparable<Pessoa> {
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	@Override
	public int compareTo(Pessoa o) {
		return cpf.compareTo(o.getCpf());
	}

}
