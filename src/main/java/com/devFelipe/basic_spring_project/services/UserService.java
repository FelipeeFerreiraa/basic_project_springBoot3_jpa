package com.devFelipe.basic_spring_project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devFelipe.basic_spring_project.entities.User;
import com.devFelipe.basic_spring_project.repositories.UserRepository;
import com.devFelipe.basic_spring_project.services.exceptions.DatabaseException;
import com.devFelipe.basic_spring_project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			// repository.deleteById(id);

			if (!repository.existsById(id))
				throw new ResourceNotFoundException("ID NOT FOUND");
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			// EmptyResultDataAccessException
//			System.out.println(e.getMessage());
//
//			e.printStackTrace();
//			e.getMessage();
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());

		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
