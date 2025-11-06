package in.nareshit.ankit.model;

import java.util.List;

import lombok.Data;
@Data
public class ErrorMsg {
	
	private Integer statusCode;
	private String status;
	private String message;
	  private List<?> list;
	public ErrorMsg(Integer statusCode, String status, String message, List<?> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.list = list;
	}

}
