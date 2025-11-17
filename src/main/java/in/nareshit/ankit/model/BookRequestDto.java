package in.nareshit.ankit.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

	@NotBlank(message = "book cannot blank")
	@Schema(description = "book", example = "enter the Book")
    private String book;
	

	@NotBlank(message = "title cannot blank")
	@Schema(description = "title", example = "enter the title")
	private String title;
	
	
	

	@NotBlank(message = "author cannot blank")
	@Schema(description = "author", example = "enter the author")
	private String author;

	@NotBlank(message = "publisherName cannot blank")
	@Schema(description = "publisherName", example = "enter the publisherName")
    private String publisherName;

	@Schema(description = "price", example = "enter the price")
	private int price;

}
