package com.matheus.springbootstarterparent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.springbootstarterparent.entities.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
