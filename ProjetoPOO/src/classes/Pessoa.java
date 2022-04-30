package classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import exceptions.DependenteException;
import exceptions.PessoaException;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private static Set<Pessoa> listPessoas = new HashSet<>();
	

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento;
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
	
	public static Set<Pessoa> getListPessoas() {
		return listPessoas;
	}
	
	public static void showListPessoas() {
		if (listPessoas.size() == 0) {
			System.out.println("O sistema não possui pessoas cadastradas.");
		}
		else {
			for (Pessoa pessoa : listPessoas) {
				System.out.println(pessoa);
			}
		}
	}
	
	public boolean cpfCheck(String cpf, String nome) {
		for (Pessoa p : getListPessoas()) {
			if(cpf.equals(p.getCpf())) {
				throw new PessoaException("O CPF " + cpf + ", do(a) " + nome + ", já está cadastrado e portanto não pode ser cadastrado novamente.");
			}
		}
		return false;
	}
	
	public boolean idadeCheck(LocalDate dataNascimento, String nome, String cpf) {
		if (Period.between(dataNascimento, LocalDate.now()).getYears() > 17) {
			throw new DependenteException("O dependente " + nome + ", CPF " + cpf + ", tem 18 anos ou mais e portanto não pode ser cadastrado como dependente.");
		}
		return false;
	}

}
