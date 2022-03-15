package com.gfmotta.desafiotdd.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfmotta.desafiotdd.dto.EventDTO;
import com.gfmotta.desafiotdd.entities.Event;
import com.gfmotta.desafiotdd.repositories.CityRepository;
import com.gfmotta.desafiotdd.repositories.EventRepository;
import com.gfmotta.desafiotdd.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository entityRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = entityRepository.getOne(id);
			convertDtoToEntity(dto, entity);
			entity = entityRepository.save(entity);
			return new EventDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado!");
		}
	}
	
	private void convertDtoToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(cityRepository.getOne(dto.getCityId()));
	}
}

