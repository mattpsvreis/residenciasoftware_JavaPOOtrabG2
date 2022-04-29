package exceptions;

public class DependenteException extends RuntimeException {

	private static final long serialVersionUID = 6378763812668275556L;

	public DependenteException(String message) {
		super(message);
	}
}
