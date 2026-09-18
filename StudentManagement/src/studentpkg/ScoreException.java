package studentpkg;

public class ScoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScoreException() {

		super();

	}

	public ScoreException(String msg) {

		super(msg);

	}

	@Override

	public String toString() {

		return "ScoreException [Score should be between 0 and 100 only]";

	}

}

 