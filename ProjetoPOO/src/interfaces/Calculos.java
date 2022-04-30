package interfaces;

public interface Calculos {
	double TetoINSS1 = 1212.00;
	double TetoINSS2 = 2427.35;
	double TetoINSS3 = 3641.03;
	double TetoINSS4 = 7087.22;
	
	double TetoIR1 = 1903.98;
	double TetoIR2 = 2826.65;
	double TetoIR3 = 3751.05;
	double TetoIR4 = 4664.68;
	
	double AliquotaINSS1 = 0.075;
	double AliquotaINSS2 = 0.09;
	double AliquotaINSS3 = 0.12;
	double AliquotaINSS4 = 0.14;
	
	double AliquotaIR1 = 0.075;
	double AliquotaIR2 = 0.15;
	double AliquotaIR3 = 0.225;
	double AliquotaIR4 = 0.275;
	
	double ParcelaINSS1 = 18.18;
	double ParcelaINSS2 = 91.00;
	double ParcelaINSS3 = 163.82;
	
	double ParcelaIR1 = 142.80;
	double ParcelaIR2 = 354.80;
	double ParcelaIR3 = 636.13;
	double ParcelaIR4 = 869.36;
	
	double ValorDependente = 189.59;
	
	public void calcSalarioLiq();
	
	public void calcIR();
	
	public void calcINSS();
}
