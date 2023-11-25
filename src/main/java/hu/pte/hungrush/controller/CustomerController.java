package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Customer;
import hu.pte.hungrush.service.CustomerService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;
    
            @GetMapping("/customer/jpa")
    public List<Customer> getAllCustomers() {
        return service.getCustomersJPA();
    }
    

    // get customer by ID in JPA
    
    @GetMapping(value="/customer/jpa/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        try {
            Customer customer = service.getCustomer(id);
            return new ResponseEntity<Customer> (customer, HttpStatus.OK);
            
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Customer> (HttpStatus.NOT_FOUND);
        }
    }
    
    // create a new customer in JPA
    
    @PostMapping(value="/addCustomer/jpa")
    public void addCustomer(@RequestBody Customer c){
        service.addCustomer(c);
    }
    
    // Delete a customer in JPA
    
    @DeleteMapping(value="/removeCustomer/jpa/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        service.deleteCustomer(id);
    }    
    
    // Edit a customer in JPA
    
    @PutMapping(value="/editCustomer/jpa/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        try {
            Customer existingCustomer = service.getCustomer(id);
            
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAddress(customer.getAddress());
            service.addCustomer(existingCustomer);
            
            return new ResponseEntity<> (HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    } 
    
    // Get all customers Stored Procedure
    @GetMapping(value="/customers/spq")
    public List<Customer> getAllCustomersSPQ() {
        return service.getAllCustomersSPQ();
    }
    

    // Add customer Stored Procedure
    @PostMapping(value="/addCustomer/spq")
    public void addCustomerSPQ(@RequestBody Customer customer) {
        service.addCustomerSPQ(customer);
    }
    
    // Delete customer Stored Procedure
    @DeleteMapping(value="/removeCustomer/spq/{id}")
    public void deleteCustomerSPQ(@PathVariable Integer id) {
        service.deleteCustomerSPQ(id);
    }
    
}
