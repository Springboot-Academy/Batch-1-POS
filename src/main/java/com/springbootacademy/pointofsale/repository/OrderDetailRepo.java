package com.springbootacademy.pointofsale.repository;

import com.springbootacademy.pointofsale.entity.Order;
import com.springbootacademy.pointofsale.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailRepo extends JpaRepository<OrderDetails,Integer> {
}
