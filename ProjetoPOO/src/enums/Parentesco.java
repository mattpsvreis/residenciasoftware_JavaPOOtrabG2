package enums;

public enum Parentesco {
	FILHO("Filho"), SOBRINHO("Sobrinho"), OUTROS("Outros");

	private String parentesco;

	private Parentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getParentesco() {
		return parentesco;
	}

}
