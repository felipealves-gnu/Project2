package packt;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class IllegalPopulationException extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalPopulationException() {
	
	}
	
	public IllegalPopulationException(String message) {
		super("IllegalPopulationException");
	}
}
