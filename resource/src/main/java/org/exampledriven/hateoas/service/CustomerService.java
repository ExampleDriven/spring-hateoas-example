package org.exampledriven.hateoas.service;

import org.exampledriven.hateoas.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService {
    List<Customer> customers;
    AtomicInteger id = new AtomicInteger();

    public CustomerService() {
        customers = new LinkedList<>();
        customers.add(new Customer(1, "Peter", "Test"));
        customers.add(new Customer(2, "Peter", "Test2"));
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(int id) {
        Optional<Customer> customer = customers.stream().filter(customer1 -> customer1.getId() == id).findFirst();
        return customer.get();
    }

    public Customer createCustomer(Customer customer) {
        return new Customer(id.incrementAndGet(), customer.getFirstName(), customer.getLastName());
    }

    public void deleteCustomer(int id) {

    }

}
