package com.gfmotta.desafiotdd.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfmotta.desafiotdd.dto.CityDTO;
import com.gfmotta.desafiotdd.entities.City;
import com.gfmotta.desafiotdd.repositories.CityRepository;
import com.gfmotta.desafiotdd.services.exceptions.DatabaseException;
import com.gfmotta.desafiotdd.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> cities = repository.findAll(Sort.by("name"));
		return cities.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City city = new City();
		city.setName(dto.getName());
		
		city = repository.save(city);
		
		return new CityDTO(city);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado!");
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Você não pode deletar esse registro no momento!");
		}
	}
}
