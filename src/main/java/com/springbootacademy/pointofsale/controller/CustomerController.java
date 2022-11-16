package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateByDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateQueryRequestDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import com.springbootacademy.pointofsale.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(path = "/save")
    public String saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        String id = customerService.addCustomer(customerSaveRequestDTO);
        return id;
    }

    @PutMapping(path = "/update")
    public String updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        String updated = customerService.updateCustomer(customerUpdateRequestDTO);
        return updated;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return customerDTO;
    }

    @GetMapping(
            path = {"/get-all-customers"}
    )
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @DeleteMapping(
            path = {"/delete-customer/{id}"}
    )
    public String deleteItem(@PathVariable(value = "id") int id) throws NotFoundException {
        boolean deletedCustomer = customerService.deleteCustomer(id);
        return "deleted";
    }

    @GetMapping(
            path = {"/get-by-name"},
            params = {"name"}
    )
    public List<CustomerDTO> getCustomerByName(@RequestParam(value = "name") String customerName) throws NotFoundException {
        List<CustomerDTO>getCustomer = customerService.getByName(customerName);
        return getCustomer;
    }

    @GetMapping(
            path = {"/get-by-active-state"}
    )
    public List<CustomerDTO> getCustomerByActiveState() throws NotFoundException{
        List<CustomerDTO> getCustomer = customerService.getAllCustomersByActiveState();
        return getCustomer;

    }

    @GetMapping(
            path = {"/get-by-active-state-only-name"}
    )
    public List<ResponseActiveCustomerDTO> getCustomerByActiveStateOnlyName() throws NotFoundException{
        List<ResponseActiveCustomerDTO> getCustomer = customerService.getAllCustomersByActiveStateOnlyName();
        return getCustomer;
    }

    @PutMapping(path = "/update-query/{id}")
    public String updateCustomerByQuery(
            @RequestBody CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO,
            @PathVariable(value = "id") int id) {
        String updated = customerService.updateCustomerByQuery(customerUpdateQueryRequestDTO,id);
        return updated;
    }

    //    ------------------HomeWork day 6--------------
    @GetMapping(
            path = {"/get-by-nic"},
            params = {"nic"}
    )
    public CustomerDTO getCustomerByNic(@RequestParam(value = "nic") String nic) {
        CustomerDTO customerDTO = customerService.getCustomerByNic(nic);
        return customerDTO;
    }

    @GetMapping(
            path = {"/get-by-id-filter"},
            params = {"id"}
    )
    public ResponseCustomerFilterDTO getCustomerByIdFilter(@RequestParam(value = "id") int id) {
        ResponseCustomerFilterDTO responseCustomerFilterDTO = customerService.getCustomerByIdByFilter(id);
        return responseCustomerFilterDTO;
    }

    @PutMapping(
            path = "/update-by-request/{id}")
    public String updateCustomerByRequest(@RequestBody CustomerUpdateByDTO customerUpdateRequestDTO,
                                          @PathVariable(value = "id") int id) {
        String updated = customerService.updateCustomerByRequest(customerUpdateRequestDTO,id);
        return updated;
    }

    @GetMapping(
            path = "/get-by-id-is-active",
            params = "id"
    )
    public CustomerDTO getCustomerByIdIsActive(@RequestParam(value = "id") int id) {
        CustomerDTO customerDTO = customerService.getCustomerByIdIsActive(id);
        return customerDTO;
    }
 }

