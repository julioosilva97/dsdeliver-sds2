package com.julioosilva97.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.julioosilva97.dsdeliver.dto.OrderDTO;
import com.julioosilva97.dsdeliver.dto.ProductDTO;
import com.julioosilva97.dsdeliver.services.OrderService;
import com.julioosilva97.dsdeliver.services.ProductService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll(){
		
		return ResponseEntity.ok().body(service.findAll());
		
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDto){
		
		orderDto = service.insert(orderDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(orderDto.getId()).toUri();
		return ResponseEntity.created(uri).body(orderDto);
		
	}
	
	@PutMapping("/{id}/delivered")
	public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id){
		
		
		return ResponseEntity.ok().body(service.setDelivered(id));
		
	}

}
