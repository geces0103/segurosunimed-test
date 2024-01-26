package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("Select distinct cust From Customer cust "
			+ "left join cust.addresses add "
			+ "Where 1 = 1 "
			+ "And (:name is null or lower(cust.name) like CONCAT('%', lower(:name), '%')) "
			+ "And (:email is null or lower(cust.email) like CONCAT('%', lower(:email), '%')) "
			+ "And (:gender is null or lower(cust.gender) = lower(:gender)) "
			+ "And (:city is null or lower(add.localidade) like CONCAT('%', lower(:city), '%')) "
			+ "And (:state is null or lower(add.uf) = lower(:state)) "
	)
	Page<Customer> findAllByFilter(
			Pageable pageable,
			@Param("name") String name,
			@Param("email") String email,
			@Param("gender") String gender,
			@Param("city") String city,
			@Param("state") String state);

}
