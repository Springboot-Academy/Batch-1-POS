package com.springbootacademy.pointofsale.repository;

import com.springbootacademy.pointofsale.entity.Item;
import com.springbootacademy.pointofsale.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order,Integer> {
}
