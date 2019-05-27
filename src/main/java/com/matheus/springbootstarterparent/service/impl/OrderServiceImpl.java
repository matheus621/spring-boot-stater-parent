package com.matheus.springbootstarterparent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.springbootstarterparent.service.OrderService;
import com.matheus.springbootstarterparent.repositories.OrderRepository;
import com.matheus.springbootstarterparent.entities.Order;


@Service
public class OrderServiceImpl implements OrderService {
	

	/**
	 * @see http://appsdeveloperblog.com/spring-boot-logging-with-loggerfactory/
	 */
Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired private OrderRepository repository;
	
	@Override
	@Transactional
	public Order post(Order entity) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));

			Order orderSave = repository.save(entity);

			logger.info(String.format("\tValor persistido: %s", orderSave.toString()));
			return orderSave;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	public Order get(Long id) {
		try {
			Optional<Order> optional = repository.findById(id);
			return optional.get();
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
			return null;
		}
	}

	@Override
	@Transactional
	public void put(Order entity) {
		try {
			repository.save(entity);
		} catch (Exception e) {
			logger.error("Error ao persistir registro.");
		}
	}

	@Override
	@Transactional
	public void delete(Order entity) {
		try {
			repository.delete(entity);
		} catch (Exception e) {
			logger.error("Error ao deletar registro.");
		}
	}

	@Override
	@Transactional
	public void patch(Order entity) {
		try {
			repository.save(entity);
		} catch (Exception e) {
			logger.error("Error ao persistir registro.");
		}
	}

	@Override
	@Transactional
	public List<Order> post(List<Order> entities) {
		try {
			List<Order> orderSave = new ArrayList<>();
			for (Order entity: entities) {
				repository.save(entity);
				entities.add(entity);
			}
			return orderSave;
		} catch (Exception e) {
			logger.error("Error ao persistir registro.");
		}
		return null;
	}

	@Override
	@Transactional
	public void put(List<Order> entities) {
		try {
			for (Order entity: entities) {
				repository.save(entity);
			}
		} catch (Exception e) {
			logger.error("Error ao persistir registro.");
		}
	}

	@Override
	@Transactional
	public void delete(List<Order> entities) {
		try {
			for (Order entity: entities) {
				repository.delete(entity);
			}
		} catch (Exception e) {
			logger.error("Error ao deletar registro.");
		}
	}

	@Override
	@Transactional
	public void patch(List<Order> entities) {
		try {
			for (Order entity: entities) {
				repository.save(entity);
			}
		} catch (Exception e) {
			logger.error("Error ao persistir registro.");
		}
	}

	@Override
	public List<Order> get() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			logger.error("Error ao recuperar registro." + e.getMessage());
		}
		return null;
	}


}
