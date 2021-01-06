package com.julioosilva97.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
