package in.nareshit.ankit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingsDto {
	private Long cusmerId;
	private Long bookId;
	private int rate;
	private String reviewText;
	
	
}
