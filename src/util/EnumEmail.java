package util;

public enum EnumEmail{
	SEND_EMAIL(1),
	GET_RECEIVED_EMAILS(2),
	GET_SENT_EMAILS(3),
	REGISTER_COUNT(4),
        AUTENTICATE(5);
	
	private int operation;
	
	private EnumEmail(int operation) {
		this.operation = operation;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}
	
	@Override
	public String toString(){
		return "Operation [type=" + operation + "]";
	}
	
}
