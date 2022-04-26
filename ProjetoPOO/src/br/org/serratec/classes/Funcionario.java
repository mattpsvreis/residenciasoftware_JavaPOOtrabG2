package br.org.serratec.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.org.serratec.interfaces.Calculos;

public class Funcionario extends Pessoa implements Calculos {
	private double salarioBruto;
	private double descontoINSS;
	private double descontoIR;
	private List<Object> listDependentes = new ArrayList<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public double getDescontoINSS() {
		return descontoINSS;
	}

	public double getDescontoIR() {
		return descontoIR;
	}
	
	public void cadastrarDependente(Object o) {
		listDependentes.add(o);
	}

	@Override
	public double calcIR() {
		if (salarioBruto < 1903.98) {
			return 0;
		}
		else if (salarioBruto < 2826.66) {
			return (((salarioBruto - (listDependentes.size() * 189.59) - calcINSS()) * 0.075) - 142.80);
		}
		else if (salarioBruto < 3751.06) {
			return (((salarioBruto - (listDependentes.size() * 189.59) - calcINSS()) * 0.15) - 354.80);
		}
		else if (salarioBruto < 4664.69) {
			return (((salarioBruto - (listDependentes.size() * 189.59) - calcINSS()) * 0.225) - 636.13);
		}
		else {
			return (((salarioBruto - (listDependentes.size() * 189.59) - calcINSS()) * 0.275) - 869.36);
		}
	}

	@Override
	public double calcINSS() {
		if (salarioBruto < 1212.00) {
			return salarioBruto * 0.075;
		}
		else if (salarioBruto < 2427.36) {
			return (salarioBruto * 0.09) - 18.18;
		}
		else if (salarioBruto < 3641.04) {
			return (salarioBruto * 0.12) - 91.00;
		}
		else if (salarioBruto < 7087.22) {
			return (salarioBruto * 0.14) - 163.82;
		}
		else {
			return (7087.22 * 0.14) - 163.82;
		}
	}

	@Override
	public double calcSalarioLiq() {
		return salarioBruto - calcINSS() - calcIR();
	}

}
