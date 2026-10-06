package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer find(@PathVariable Long id) {
        return customerService.find(id);
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getEmail(),
                savedCustomer.getSalary()
        );
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.find(id);

        if (existingCustomer == null) {
            customer.setId(id);
            existingCustomer = customer;
        } else {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setSalary(customer.getSalary());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setAccounts(customer.getAccounts());
        }

        Customer savedCustomer = customerService.save(existingCustomer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getEmail(),
                savedCustomer.getSalary()
        );
    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}