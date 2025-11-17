package in.nareshit.ankit.model;

import java.util.List;

import lombok.Data;
@Data
public class OrderModuleDto {

	private Long custmerId;
	private List<String> title;
}
