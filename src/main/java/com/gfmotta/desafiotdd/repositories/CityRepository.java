package com.gfmotta.desafiotdd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gfmotta.desafiotdd.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
