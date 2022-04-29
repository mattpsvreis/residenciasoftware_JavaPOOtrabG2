package exceptions;

public class PessoaException extends RuntimeException {

	private static final long serialVersionUID = -3553070210707891759L;

	public PessoaException(String message) {
		super(message);
	}
}
