package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerForm;
import com.example.api.domain.dto.CustomerView;
import com.example.api.domain.mapper.BaseMapper;
import com.example.api.domain.mapper.CustomerMapper;
import com.example.api.exception.GlobalException;
import com.example.api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomerService extends AbstractService<Customer, CustomerForm, CustomerView, Long> {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	public Page<CustomerView> findByFilter(Pageable pageable, String name, String email, String gender, String city, String state) {

		log.info(">> findByFilter [name={}, email={}, gender={}, city={}, state={}]", name, email, gender, city, state);

		Page<Customer> page = repository.findAllByFilter(pageable, name, email, gender, city, state);

		List<CustomerView> views =
				page.getContent().stream()
						.map(emp -> getConverter().entityToView(emp))
						.collect(Collectors.toList());

		log.info("<< findByFilter [views={}]", views.size());

		return new PageImpl<>(views, pageable, page.getTotalElements());
	}

	public CustomerView update(Long id, CustomerForm form) {
		try {
			log.info(">> update [id={}, form={}]", id, form);
			Optional<Customer> customer = getRepository().findById(id);

			if(customer.isEmpty()) {
				throw new GlobalException("Cliente não encontrado");
			}

			Customer entity = getConverter().formToEntity(form);
			entity.setId(customer.get().getId());

			entity = getRepository().save(entity);
			log.info("<< update [entity={}]", entity);
			return getConverter().entityToView(entity);
		} catch (Exception ex) {
			log.error("<< update [id={}]", id, ex);
			throw new GlobalException("Não foi possivel atualizar cliente");
		}

	}

	@Override
	protected JpaRepository<Customer, Long> getRepository() {
		return repository;
	}

	@Override
	protected BaseMapper<Customer, CustomerForm, CustomerView> getConverter() {
		return mapper;
	}
}
