package it.univaq.disim.swa.visitaq.business;

public class VisitaqBusinessException extends Exception {

	public VisitaqBusinessException() {
		super();
	}
	public VisitaqBusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	public VisitaqBusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	public VisitaqBusinessException(String message) {
		super(message);
	}
	public VisitaqBusinessException(Throwable cause) {
		super(cause);
	}
}
