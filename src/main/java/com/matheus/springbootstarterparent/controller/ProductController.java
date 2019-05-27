package com.matheus.springbootstarterparent.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.springbootstarterparent.entities.Product;
import com.matheus.springbootstarterparent.interfaces.GenericOperationsController;
import com.matheus.springbootstarterparent.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController implements GenericOperationsController<Product>{
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired private ProductServiceImpl productService;

	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Product> post(@RequestBody Product entity) {
		try {
			productService.post(entity);
			logger.info("Registro inserido");

			Link link = linkTo(OrderController.class).slash(entity.getIdProduct()).withSelfRel();
			return new Resource<>(entity, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método POST.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody Product entity) {
		try {
			productService.put(entity);
			logger.info(String.format("Registro atualizado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PUT.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Product entity) {
		try {
			productService.delete(entity);
			logger.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PUT.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE,
			MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Product> get() {
		try {
			List<Product> entities = productService.get();

			logger.info(String.format("Registro(s) recuperados(s): %s", entities.toString()));

			for (final Product entity : entities) {
				Link selfLink = linkTo(ProductController.class)
						.slash(entity.getIdProduct())
						.withSelfRel();
				entity.add(selfLink);
			}
			Link link = linkTo(ProductController.class).withSelfRel();
			return new Resources<>(entities, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE,
			MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Product> get(@PathVariable Long id) {
		try {
			Product entity = productService.get(id);
			logger.info(String.format("Registro recuperado: %s", entity.toString()));

			Link link = linkTo(ProductController.class).slash(entity.getIdProduct()).withSelfRel();
			return new Resource<>(entity, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(Product entity) {
		try {
			productService.patch(entity);
			logger.info(String.format("Registro atualizado: %s",entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s", e.getMessage()));
		}
	}
	
}
