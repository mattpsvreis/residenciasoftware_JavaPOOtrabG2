package br.com.serratec;

import java.time.LocalDate;

import br.org.serratec.classes.Dependente;
import br.org.serratec.classes.Pessoa;
import br.org.serratec.enums.Parentesco;

public class Testagem {

	public static void main(String[] args) {
		Pessoa teste = new Dependente("João", "222", LocalDate.of(1998, 10, 7), Parentesco.Filho);
		
		System.out.println(teste);

	}

}
