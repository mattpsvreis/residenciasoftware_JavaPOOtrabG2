package classes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import enums.Parentesco;

public class Dependente extends Pessoa  {
	private Parentesco parentesco;
	private static Set<Dependente> listDependentes = new HashSet<>();

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nParentesco: " + parentesco;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}
	
	public static Set<Dependente> getListDependentes() {
		return listDependentes;
	}
	
	public static void showListDependentes() {
		if (listDependentes.size() == 0) {
			System.out.println("O sistema não possui dependentes cadastrados.");
		}
		else {
			for (Dependente dependente : listDependentes) {
				System.out.println(dependente);
			}
		}
	}

}
