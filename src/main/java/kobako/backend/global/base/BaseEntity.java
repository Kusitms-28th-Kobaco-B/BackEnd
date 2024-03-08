package kobako.backend.global.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

	@Column(
		nullable = false,
		insertable = false,
		updatable = false,
		columnDefinition = "datetime default CURRENT_TIMESTAMP")
	@CreatedDate
	@JsonFormat(
		shape = STRING,
		pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

	@Column(
		nullable = false,
		insertable = false,
		columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	@LastModifiedDate
	@JsonFormat(
		shape = STRING,
		pattern = "yyyy-MM-dd")
	private LocalDate updatedDate;
}
