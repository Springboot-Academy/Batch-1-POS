package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateByDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateQueryRequestDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import com.springbootacademy.pointofsale.entity.Customer;
import com.springbootacademy.pointofsale.exception.EntryDuplicateException;
import com.springbootacademy.pointofsale.repository.CustomerRepo;
import com.springbootacademy.pointofsale.service.CustomerService;
import com.springbootacademy.pointofsale.util.mappers.CustomerMapper;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = new Customer(
                customerSaveRequestDTO.getCustomerName(),
                customerSaveRequestDTO.getCustomerAddress(),
                customerSaveRequestDTO.getCustomerSalary(),
                customerSaveRequestDTO.getContactNumbers(),
                customerSaveRequestDTO.getNic(),
                false
        );
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerName() + " saved";
        } else {
            System.out.println("customer id already exists");
            return "customer id already exists";
        }
    }

    @Override
    public String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        if (customerRepo.existsById(customerUpdateRequestDTO.getCustomerId())) {
            Customer customer = customerRepo.getById(customerUpdateRequestDTO.getCustomerId());
            customer.setCustomerName(customerUpdateRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateRequestDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateRequestDTO.getCustomerSalary());
            customer.setContactNumbers(customerUpdateRequestDTO.getContactNumbers());
            customer.setNic(customerUpdateRequestDTO.getNic());
            customer.setActiveState(customerUpdateRequestDTO.isActiveState());

            return customerRepo.save(customer).getCustomerName() + "updated";

        } else {
//            System.out.println("this customer not in database");
//            return "this customer not in database";
            throw new EntryDuplicateException("not in database");
        }

    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.get().getCustomerId(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getCustomerSalary(),
//                    customer.get().getContactNumbers(),
//                    customer.get().getNic(),
//                    customer.get().isActiveState()
//            );
//            return customerDTO;
//            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;

        } else {
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

//        for(Customer c:getCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    c.getCustomerId(),
//                    c.getCustomerName(),
//                    c.getCustomerAddress(),
//                    c.getCustomerSalary(),
//                    c.getContactNumbers(),
//                    c.getNic(),
//                    c.isActiveState()
//            );
//            customerDTOList.add(customerDTO);
//        }
        List<CustomerDTO> customerDTOS = modelMapper.
                map(getCustomers, new TypeToken<List<CustomerDTO>>() {
                }.getType());
        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(int id) throws NotFoundException {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            throw new NotFoundException("not found customer for this id");
        }
        return true;
    }

    @Override
    public List<CustomerDTO> getByName(String customerName) throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(customerName);
        if (customers.size() != 0) {
            List<CustomerDTO> customerDTOS = modelMapper.
                    map(customers, new TypeToken<List<CustomerDTO>>() {
                    }.getType());
            return customerDTOS;
        } else {
            throw new NotFoundException("no results");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState() throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if (customers.size() != 0) {
            List<CustomerDTO> customerDTOS = customerMapper.entityListToDtoList(customers);
            return customerDTOS;
        } else {
            throw new NotFoundException("no active customers found");
        }
    }

    @Override
    public List<ResponseActiveCustomerDTO> getAllCustomersByActiveStateOnlyName() throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if (customers.size() != 0) {
            List<ResponseActiveCustomerDTO> customerDTOS = customerMapper.entityListToDtoListOnlyName(customers);
            return customerDTOS;
        } else {
            throw new NotFoundException("no active customers found");
        }
    }

    @Override
    public String updateCustomerByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO, int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.updateCustomerByQuery(customerUpdateQueryRequestDTO.getCustomerName(), customerUpdateQueryRequestDTO.getNic(), id);
            return "updated succesfull id " + id;
        } else {
            System.out.println("no customer found for this id " + id);
            return "no customer found for this id " + id;
        }

    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        Optional<Customer> customer = customerRepo.findByNicEquals(nic);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            return customerDTO;
        } else {
            throw new com.springbootacademy.pointofsale.exception.NotFoundException("not found mmmmmmmmmmm");
        }

    }

    @Override
    public ResponseCustomerFilterDTO getCustomerByIdByFilter(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            ResponseCustomerFilterDTO responseCustomerFilterDTO = customerMapper.entityToResponseDto(customer.get());
            return responseCustomerFilterDTO;

        } else {
            throw new com.springbootacademy.pointofsale.exception.NotFoundException("not found");
        }
    }

    @Override
    public String updateCustomerByRequest(CustomerUpdateByDTO customerUpdateByDTO, int id) {
        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.getById(id);

            customer.setCustomerName(customerUpdateByDTO.getCustomerName());
            customer.setCustomerSalary(customerUpdateByDTO.getCustomerSalary());
            customer.setNic(customerUpdateByDTO.getNic());


            return customerRepo.save(customer).getCustomerName() + "updated success " + id;

        } else {
//            System.out.println("this customer not in database");
//            return "this customer not in database";
            throw new EntryDuplicateException("not in database");
        }
    }

    @Override
    public CustomerDTO getCustomerByIdIsActive(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            if (customer.get().isActiveState() == true) {
                CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
                return customerDTO;
            } else {
                System.out.println("this is inactive customer");
            }
        } else {
           throw new com.springbootacademy.pointofsale.exception.NotFoundException("not found");
        }
        return new CustomerDTO(
                customer.get().getCustomerName()+" is not active "+ id
        );
    }






}
