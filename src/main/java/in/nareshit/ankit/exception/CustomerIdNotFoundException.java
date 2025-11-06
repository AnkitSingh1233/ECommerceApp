package in.nareshit.ankit.exception;

public class CustomerIdNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 4040585432907566415L;

	public CustomerIdNotFoundException(String msg) {
		 super(msg);
	}
	
}
