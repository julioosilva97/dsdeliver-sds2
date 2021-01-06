package com.julioosilva97.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julioosilva97.dsdeliver.dto.OrderDTO;
import com.julioosilva97.dsdeliver.dto.ProductDTO;
import com.julioosilva97.dsdeliver.entities.Order;
import com.julioosilva97.dsdeliver.entities.Product;
import com.julioosilva97.dsdeliver.repositories.OrderRepository;
import com.julioosilva97.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream()
				.map(order -> new OrderDTO(order))
				.collect(Collectors.toList());
	}
	

}
