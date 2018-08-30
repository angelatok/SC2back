package sg.com.account.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateImageRequest {

	@NotNull
	@Size(min=9)
	private String icNumber;
	@NotNull
	private String imageUr;
	
}
