package com.example.lodgings.service;

import com.example.lodgings.entity.Customer;
import com.example.lodgings.exception.ResourceNotFoundException;
import com.example.lodgings.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));

        customer.setName(updatedCustomer.getName());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setAge(updatedCustomer.getAge());
        customer.setPhoneNo(updatedCustomer.getPhoneNo());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setGender(updatedCustomer.getGender());
        customer.setDate(updatedCustomer.getDate());
        customer.setTime(updatedCustomer.getTime());

        return customerRepository.save(customer);
    }

    public String deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", id);
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }
}
