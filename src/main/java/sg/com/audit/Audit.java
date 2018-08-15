package sg.com.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.auditing.DateTimeProvider;

import lombok.Data;

@Data
public class Audit {
	@CreatedBy
	private String createdBy;
	@CreatedDate
	private DateTimeProvider createDate;
	@LastModifiedDate
	private DateTimeProvider modifiedDate;
}
