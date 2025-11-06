package in.nareshit.ankit.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {
	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="custmer_id")
	private Long id;
	@Column(name = "custName")
	private String custName;
	@Column(name = "email")
	private String email;
	@CreationTimestamp
	@Column(name = "createdDate")
	public LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(name = "updatedDate")
	public LocalDateTime updatedDate;

}
