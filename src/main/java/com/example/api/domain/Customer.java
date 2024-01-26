package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.*;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractBaseId {

	@Column(nullable = false)
	private String name;

	@Email
	@NotEmpty
	@Column(nullable = false)
	private String email;

	@NotEmpty
	@Column(nullable = false)
	private String gender;

	@JsonManagedReference
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();
}
