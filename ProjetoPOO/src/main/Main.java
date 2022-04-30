package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Funcionario;
import enums.Parentesco;

public class Main {

	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o caminho do arquivo a ser lido:");
		String readPath = sc.nextLine();
		System.out.println("Digite o caminho do arquivo a ser escrito:");
		String writePath = sc.nextLine();
		sc.close();

		try {
			File file = new File(readPath);

			Scanner sc2 = new Scanner(file);

			while (sc2.hasNext()) {
				String line = sc2.nextLine();

				if (!line.isEmpty()) {
					String dataLine[] = line.split(";");

					if (!dataLine[3].equals("FILHO") && !dataLine[3].equals("SOBRINHO") && !dataLine[3].equals("OUTROS")) {
						String nome = dataLine[0];
						String cpf = dataLine[1];
						double salario = Double.parseDouble(dataLine[3]);

						String dataComposta[] = new String[3];
						dataComposta[0] = dataLine[2].substring(0,4);
						dataComposta[1] = dataLine[2].substring(4,6);
						dataComposta[2] = dataLine[2].substring(6,8);
						int ano = Integer.parseInt(dataComposta[0]);
						int mes = Integer.parseInt(dataComposta[1]);
						int dia = Integer.parseInt(dataComposta[2]);

						funcionarios.add(new Funcionario(nome, cpf, LocalDate.of(ano, mes, dia), salario));
					} else {
						String nome = dataLine[0];
						String cpf = dataLine[1];
						String parentesco = dataLine[3];

						String dataComposta[] = new String[3];
						dataComposta[0] = dataLine[2].substring(0,4);
						dataComposta[1] = dataLine[2].substring(4,6);
						dataComposta[2] = dataLine[2].substring(6,8);
						int ano = Integer.parseInt(dataComposta[0]);
						int mes = Integer.parseInt(dataComposta[1]);
						int dia = Integer.parseInt(dataComposta[2]);

						funcionarios.get(funcionarios.size() - 1).cadastrarDependente(nome, cpf, LocalDate.of(ano, mes, dia), Parentesco.valueOf(Parentesco.class, parentesco));
					}
				}

			}
			sc2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(writePath));
			
			int i = 0;
			
			for (Funcionario funcionario : funcionarios) {
				i++;
				
				funcionario.calcINSS();
				funcionario.calcIR();
				funcionario.calcSalarioLiq();
				
				bw.write(funcionario.getNome() + ";"
						+ funcionario.getCpf() + ";"
						+ String.format("%.2f", funcionario.getDescontoINSS()) + ";"
						+ String.format("%.2f", funcionario.getDescontoIR()) + ";"
						+ String.format("%.2f", funcionario.getSalarioLiquido()));
				
				if (i < funcionarios.size()) {
					bw.newLine();
				}
			}
			
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
