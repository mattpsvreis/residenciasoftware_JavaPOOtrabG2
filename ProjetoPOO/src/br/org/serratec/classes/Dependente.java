package br.org.serratec.classes;

import java.time.LocalDate;
import java.time.Period;

import br.org.serratec.enums.Parentesco;
import br.org.serratec.exceptions.DependenteException;

public class Dependente extends Pessoa  {
	private Parentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
		
		if (Period.between(dataNascimento, LocalDate.now()).getYears() > 17) {
			throw new DependenteException("Dependente tem que ser menor que 18 anos!");
		}
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

}
