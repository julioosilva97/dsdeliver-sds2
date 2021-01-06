package com.julioosilva97.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julioosilva97.dsdeliver.dto.ProductDTO;
import com.julioosilva97.dsdeliver.entities.Product;
import com.julioosilva97.dsdeliver.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream()
				.map(product -> new ProductDTO(product))
				.collect(Collectors.toList());
	}
	

}
