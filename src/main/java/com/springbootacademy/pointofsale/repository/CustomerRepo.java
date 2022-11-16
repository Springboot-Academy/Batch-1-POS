package com.springbootacademy.pointofsale.repository;

import com.springbootacademy.pointofsale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer> findAllByCustomerNameEquals(String customerName);

    List<Customer> findAllByActiveStateEquals(boolean b);

    @Modifying
    @Query(value = "update customer set customer_name=?1,nic=?2 where customer_id=?3",nativeQuery = true)
    void updateCustomerByQuery(String customerName, String nic, int id);

    Optional<Customer> findByNicEquals(String nic);
}
