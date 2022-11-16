package com.springbootacademy.pointofsale.service;
import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateByDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateQueryRequestDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import javassist.NotFoundException;

import java.util.List;


public interface CustomerService {
    String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);
    String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerDTO getCustomerById(int id);

    List<CustomerDTO> getAllCustomers();

    boolean deleteCustomer(int id) throws NotFoundException;

    List<CustomerDTO> getByName(String customerName) throws NotFoundException;

    List<CustomerDTO> getAllCustomersByActiveState() throws NotFoundException;

    List<ResponseActiveCustomerDTO> getAllCustomersByActiveStateOnlyName() throws NotFoundException;

    String updateCustomerByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO,int id);

    CustomerDTO getCustomerByNic(String nic);

    ResponseCustomerFilterDTO getCustomerByIdByFilter(int id);

    String updateCustomerByRequest(CustomerUpdateByDTO customerUpdateRequestDTO, int id);

    CustomerDTO getCustomerByIdIsActive(int id);
}
