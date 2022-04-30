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
		
		try {
			if (Pessoa.getListPessoas().size() == 0) {
				listFuncionarios.add(this);
				Pessoa.getListPessoas().add(this);
			}
			else if (cpfCheck(cpf, nome) == false) {
				listFuncionarios.add(this);
				Pessoa.getListPessoas().add(this);
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		 return super.toString() 
				+ "\nSalário Bruto: " + salarioBruto
				+ "\nDesconto INSS: R$" + String.format("%.2f", descontoINSS)
		 		+ "\nDesconto IR: R$" + String.format("%.2f", descontoIR)
		 		+ "\nSalário Líquido: R$" + String.format("%.2f", salarioLiquido);
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
		if (salarioBruto <= TetoIR1) {
			this.descontoIR = 0;
		}
		else if (salarioBruto <= TetoIR2) {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * ValorDependente) - getDescontoINSS()) * AliquotaIR1) - ParcelaIR1);
		}
		else if (salarioBruto <= TetoIR3) {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * ValorDependente) - getDescontoINSS()) * AliquotaIR2) - ParcelaIR2);
		}
		else if (salarioBruto <= TetoIR4) {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * ValorDependente) - getDescontoINSS()) * AliquotaIR3) - ParcelaIR3);
		}
		else {
			this.descontoIR = (((salarioBruto - (listDependentes.size() * ValorDependente) - getDescontoINSS()) * AliquotaIR4) - ParcelaIR4);
		}
	}

	@Override
	public void calcINSS() {
		if (salarioBruto <= TetoINSS1) {
			this.descontoINSS = salarioBruto * AliquotaINSS1;
		}
		else if (salarioBruto <= TetoINSS2) {
			this.descontoINSS = (salarioBruto * AliquotaINSS2) - ParcelaINSS1;
		}
		else if (salarioBruto <= TetoINSS3) {
			this.descontoINSS = (salarioBruto * AliquotaINSS3) - ParcelaINSS2;
		}
		else if (salarioBruto <= TetoINSS4) {
			this.descontoINSS = (salarioBruto * AliquotaINSS4) - ParcelaINSS3;
		}
		else {
			this.descontoINSS = (TetoINSS4 * AliquotaINSS4) - ParcelaINSS3;
		}
	}

	@Override
	public void calcSalarioLiq() {
		this.salarioLiquido = salarioBruto - getDescontoINSS() - getDescontoIR();
	}

}
