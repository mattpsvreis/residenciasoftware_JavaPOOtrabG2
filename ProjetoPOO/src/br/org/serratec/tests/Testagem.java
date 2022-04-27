package br.org.serratec.tests;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.org.serratec.classes.Dependente;
import br.org.serratec.classes.Funcionario;
import br.org.serratec.enums.Parentesco;

public class Testagem {

	public static void main(String[] args) {
		Set<Funcionario> funcionarios = new HashSet<>();
		Set<Dependente> dependentes = new HashSet<>();

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Digite o caminho do arquivo:");
			String pathread = sc.nextLine();

			File file = new File(pathread);

			Scanner sc2 = new Scanner(file);

			while (sc2.hasNext()) {
				String line = sc2.nextLine();
				if (!line.isEmpty()) {
					String dataLine[] = line.split(";");
					
					if (!dataLine[3].equals("FILHO") && !dataLine[3].equals("SOBRINHO") && !dataLine[3].equals("OUTROS")) {
						String nome = dataLine[0];
						String cpf = dataLine[1];
						String data = dataLine[2];
						double salario = Double.parseDouble(dataLine[3]);

						String dataComposta[] = dataLine[2].split("/");
						int dataDia = Integer.parseInt(dataComposta[0]);
						int dataMes = Integer.parseInt(dataComposta[1]);
						int dataAno = Integer.parseInt(dataComposta[2]);
						
						funcionarios.add(new Funcionario(nome, cpf, LocalDate.of(dataAno, dataMes, dataDia), salario));
					} else {
						String nome = dataLine[0];
						String cpf = dataLine[1];
						String data = dataLine[2];
						String parentesco = dataLine[3];
						
						String dataComposta[] = dataLine[2].split("/");
						int dataDia = Integer.parseInt(dataComposta[0]);
						int dataMes = Integer.parseInt(dataComposta[1]);
						int dataAno = Integer.parseInt(dataComposta[2]);
						
						dependentes.add(new Dependente(nome, cpf, LocalDate.of(dataAno, dataMes, dataDia), Parentesco.valueOf(Parentesco.class, parentesco)));
					}

				}

			}
			sc.close();
			sc2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for (Funcionario funcionario : funcionarios) {
			
		}
		
		for (Dependente dependente : dependentes) {
			
		}
	}
}
