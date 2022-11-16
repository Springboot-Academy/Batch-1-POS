package com.springbootacademy.pointofsale.util.mappers;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import com.springbootacademy.pointofsale.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO entityToDto(Customer customer);
    List<CustomerDTO> entityListToDtoList(List<Customer> customers);
    List<ResponseActiveCustomerDTO> entityListToDtoListOnlyName(List<Customer> customers);
    ResponseCustomerFilterDTO entityToResponseDto(Customer customer);
}
