package classes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import enums.Parentesco;
import interfaces.Calculos;

public class Funcionario extends Pessoa implements Calculos {
	private double salarioBruto;
	private double descontoINSS;
	private double descontoIR;
	private double salarioLiquido;
	private Set<Dependente> listDependentes = new HashSet<>();
	private static Set<Funcionario> listFuncionarios = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		
		if (Pessoa.getListPessoas().size() == 0) {
			listFuncionarios.add(this);
			Pessoa.getListPessoas().add(this);
		}
		else if (cpfCheck(cpf, nome) == false) {
			listFuncionarios.add(this);
			Pessoa.getListPessoas().add(this);
		}
	}
	
	@Override
	public String toString() {
		 return super.toString() + "\nSalário Bruto: " + salarioBruto;
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

	public double getSalarioLiquido() {
		return salarioLiquido;
	}
	
	public Set<Dependente> getListDependentes() {
		return listDependentes;
	}
	
	public static void showListFuncionarios() {
		if (listFuncionarios.size() == 0) {
			System.out.println("O sistema não possui funcionários cadastrados.");
		}
		else {
			for (Funcionario funcionario : listFuncionarios) {
				System.out.println(funcionario);
			}
		}
	}

	public void cadastrarDependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
		Dependente d = new Dependente(nome, cpf, dataNascimento, parentesco);
		
		try {
			if(idadeCheck(dataNascimento, nome, cpf) == false) {
				if (Pessoa.getListPessoas().size() == 0){
					Dependente.getListDependentes().add(d);
					Pessoa.getListPessoas().add(d);
					listDependentes.add(d);
				}
				else if (cpfCheck(cpf, nome) == false) {
					Dependente.getListDependentes().add(d);
					Pessoa.getListPessoas().add(d);
					listDependentes.add(d);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void calcIR() {
		if (salarioBruto < 1903.98) {
			this.descontoIR = 0;
		}
		else if (salarioBruto < 2826.66) {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * 189.59) - getDescontoINSS()) * 0.075) - 142.80);
		}
		else if (salarioBruto < 3751.06) {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * 189.59) - getDescontoINSS()) * 0.15) - 354.80);
		}
		else if (salarioBruto < 4664.69) {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * 189.59) - getDescontoINSS()) * 0.225) - 636.13);
		}
		else {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * 189.59) - getDescontoINSS()) * 0.275) - 869.36);
		}
	}

	@Override
	public void calcINSS() {
		if (salarioBruto < 1212.00) {
			this.descontoINSS = salarioBruto * 0.075;
		}
		else if (salarioBruto < 2427.36) {
			this.descontoINSS = (salarioBruto * 0.09) - 18.18;
		}
		else if (salarioBruto < 3641.04) {
			this.descontoINSS = (salarioBruto * 0.12) - 91.00;
		}
		else if (salarioBruto < 7087.22) {
			this.descontoINSS = (salarioBruto * 0.14) - 163.82;
		}
		else {
			this.descontoINSS = (7087.22 * 0.14) - 163.82;
		}
	}

	@Override
	public void calcSalarioLiq() {
		this.salarioLiquido = salarioBruto - getDescontoINSS() - getDescontoIR();
	}

}
