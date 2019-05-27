package com.matheus.springbootstarterparent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.springbootstarterparent.entities.People;
import com.matheus.springbootstarterparent.repositories.PeopleRepository;
import com.matheus.springbootstarterparent.service.PeopleService;



@Service
public class PeopleServiceImpl implements PeopleService {
	
	 Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);

	    @Autowired private PeopleRepository repository;

	    @Override
	    public People post(People entity) {
	        try {
	            logger.debug("\tMétodo POST executado.");
	            logger.debug("\tMétodo POST invocado");
	            logger.debug(String.format("\tValor recebido: %s", entity.toString()));

	            People entitySaved = repository.save(entity);

	            logger.info(String.format("\tValor persistido: %s", entitySaved.toString()));
	            return entitySaved;
	        } catch (Exception e) {
	            logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
	            return null;
	        }
	    }

	    @Override
	    public People get(Long id) {
	        try {
	            Optional<People> optional = repository.findById(id);
	            return optional.get();
	        } catch (Exception e) {
	            logger.error("Error ao recuperar método GET.");
	            return null;
	        }
	    }

	    @Override
	    public void put(People entity) {
	        try {
	            repository.save(entity);
	        } catch (Exception e) {
	            logger.error("Error ao persistir registro.");
	        }
	    }

	    @Override
	    public void delete(People entity) {
	        try {
	            repository.delete(entity);
	        } catch (Exception e) {
	            logger.error("Error ao deletar registro.");
	        }
	    }

	    @Override
	    public void patch(People entity) {
	        try {
	            repository.save(entity);
	        } catch (Exception e) {
	            logger.error("Error ao persistir registro.");
	        }
	    }

	    @Override
	    public List<People> post(List<People> entities) {
	        try {
	            List<People> peopleSave = new ArrayList<>();
	            for (People entity: entities) {
	                repository.save(entity);
	                entities.add(entity);
	            }
	            return peopleSave;
	        } catch (Exception e) {
	            logger.error("Error ao persistir registro.");
	        }
	        return null;
	    }

	    @Override
	    public List<People> get() {
	        try {
	            return repository.findAll();
	        } catch (Exception e) {
	            logger.error("Error ao recuperar registro." + e.getMessage());
	        }
	        return null;
	    }

	    @Override
	    public void put(List<People> entities) {
	        try {
	            for (People entity: entities) {
	                repository.save(entity);
	            }
	        } catch (Exception e) {
	            logger.error("Error ao persistir registro.");
	        }
	    }

	    @Override
	    public void delete(List<People> entities) {
	        try {
	            for (People entity: entities) {
	                repository.delete(entity);
	            }
	        } catch (Exception e) {
	            logger.error("Error ao deletar registro.");
	        }
	    }

	    @Override
	    public void patch(List<People> entities) {
	        try {
	            for (People entity: entities) {
	                repository.save(entity);
	            }
	        } catch (Exception e) {
	            logger.error("Error ao persistir registro.");
	        }
	    }


}
