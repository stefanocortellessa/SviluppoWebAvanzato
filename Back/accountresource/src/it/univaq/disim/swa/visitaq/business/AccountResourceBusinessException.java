package it.univaq.disim.swa.visitaq.business;

public class AccountResourceBusinessException extends Exception {

	public AccountResourceBusinessException() {
		super();
	}

	public AccountResourceBusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AccountResourceBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountResourceBusinessException(String message) {
		super(message);
	}

	public AccountResourceBusinessException(Throwable cause) {
		super(cause);
	}
	
}
