package it.univaq.disim.swa.visitaq.business;

public class AccountManagerBusinessException extends Exception {

	public AccountManagerBusinessException() {
		super();
	}

	public AccountManagerBusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AccountManagerBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountManagerBusinessException(String message) {
		super(message);
	}

	public AccountManagerBusinessException(Throwable cause) {
		super(cause);
	}
	
}
