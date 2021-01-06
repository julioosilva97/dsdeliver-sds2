package com.julioosilva97.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julioosilva97.dsdeliver.dto.OrderDTO;
import com.julioosilva97.dsdeliver.dto.ProductDTO;
import com.julioosilva97.dsdeliver.entities.Order;
import com.julioosilva97.dsdeliver.entities.OrderStatus;
import com.julioosilva97.dsdeliver.entities.Product;
import com.julioosilva97.dsdeliver.repositories.OrderRepository;
import com.julioosilva97.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream()
				.map(order -> new OrderDTO(order))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO orderDto){
		
		Order order = new Order(null,orderDto.getAddress(),orderDto.getLatitude(),
				orderDto.getLongitude(),Instant.now(),OrderStatus.PENDING);
		
		for(ProductDTO productDto : orderDto.getProducts()) {
			Product product = productRepository.getOne(productDto.getId());
			order.getProducts().add(product);
		}
		
		/*orderDto.getProducts().forEach(productDto -> {
			Product product = productRepository.getOne(productDto.getId());
			order.getProducts().add(product);
		});*/
		
		order = repository.save(order);
		
		return new OrderDTO(order) ;
		
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id){
		
		Order order = repository.getOne(id);
		
		order.setStatus(OrderStatus.DELIVERED);
		
		order = repository.save(order);
		
		return new OrderDTO(order);
		
	}
	
	

}
