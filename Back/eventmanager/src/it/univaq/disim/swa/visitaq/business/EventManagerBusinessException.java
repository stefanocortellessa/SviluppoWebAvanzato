package it.univaq.disim.swa.visitaq.business;

public class EventManagerBusinessException extends Exception {

	public EventManagerBusinessException() {
		super();
	}
	public EventManagerBusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	public EventManagerBusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	public EventManagerBusinessException(String message) {
		super(message);
	}
	public EventManagerBusinessException(Throwable cause) {
		super(cause);
	}
}
