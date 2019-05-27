package com.matheus.springbootstarterparent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.springbootstarterparent.service.ProductService;
import com.matheus.springbootstarterparent.repositories.ProductRepository;
import com.matheus.springbootstarterparent.entities.Product;;


@Service
public class ProductServiceImpl implements ProductService {
	

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product post(Product entity) {
        try {
            logger.debug("\tMétodo POST executado.");
            logger.debug("\tMétodo POST invocado");
            logger.debug(String.format("\tValor recebido: %s", entity.toString()));

            Product productSave = productRepository.save(entity);

            logger.info(String.format("\tValor persistido: %s", productSave.toString()));
            return productSave;
        } catch (Exception e) {
            logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
            return null;
        }
    }

    @Override
    public Product get(Long id) {
        try {
            Optional<Product> optional = productRepository.findById(id);
            return optional.get();
        } catch (Exception e) {
            logger.error("Error ao recuperar método GET.");
            return null;
        }
    }

    @Override
    public void put(Product entity) {
        try {
        	productRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

    @Override
    public void delete(Product entity) {
        try {
        	productRepository.delete(entity);
        } catch (Exception e) {
            logger.error("Error ao deletar registro.");
        }
    }

    @Override
    public void patch(Product entity) {
        try {
        	productRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

    @Override
    public List<Product> post(List<Product> entities) {
        try {
            List<Product> productSave = new ArrayList<>();
            for (Product entity: entities) {
            	productRepository.save(entity);
                entities.add(entity);
            }
            return productSave;
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
        return null;
    }

    @Override
    public List<Product> get() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            logger.error("Error ao recuperar registro." + e.getMessage());
        }
        return null;
    }

    @Override
    public void put(List<Product> entities) {
        try {
            for (Product entity: entities) {
            	productRepository.save(entity);
            }
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

    @Override
    public void delete(List<Product> entities) {
        try {
            for (Product entity: entities) {
            	productRepository.delete(entity);
            }
        } catch (Exception e) {
            logger.error("Error ao deletar registro.");
        }
    }

    @Override
    public void patch(List<Product> entities) {
        try {
            for (Product entity: entities) {
            	productRepository.save(entity);
            }
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }


}
