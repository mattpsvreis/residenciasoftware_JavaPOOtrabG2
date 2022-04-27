package br.org.serratec.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.org.serratec.classes.Funcionario;

public class Test {
	public static void main(String[] args) {
		List<Object> listtest = new ArrayList<>();
		
		Funcionario f1 = new Funcionario("João", "Banana", LocalDate.now(), 12412);
		
		listtest.add(f1);
		
		System.out.println(listtest.contains("Banana"));
		
	}
}
