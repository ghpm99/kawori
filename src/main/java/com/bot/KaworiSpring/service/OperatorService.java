package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.repository.OperatorRepository;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class OperatorService.
 */
@Service
public class OperatorService {

	/** The operator repository. */
	private OperatorRepository operatorRepository;

	/**
	 * Instantiates a new operator service.
	 *
	 * @param operatorRepository the operator repository
	 */
	@Autowired
	public OperatorService(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the operator
	 */
	public Operator findById(String id) {
		return operatorRepository.findById(id).orElseGet(() -> {
			Operator user = new Operator();
			user.setId(id);
			user.setWebAuthorized(false);
			user.setRole("USER");
			return user;
		});
	}

	/**
	 * Save.
	 *
	 * @param operator the operator
	 * @return the operator
	 */
	public Operator save(Operator operator) {
		operator.setNewRecord(false);
		return operatorRepository.save(operator);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Operator> findAll() {
		return operatorRepository.findAll();
	}

	/**
	 * Count.
	 *
	 * @return the long
	 */
	public long count() {
		return operatorRepository.count();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Operator> findAll(Pageable pageable){
		return operatorRepository.findAll(pageable);
	}
	
}
