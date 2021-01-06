package com.julioosilva97.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julioosilva97.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
