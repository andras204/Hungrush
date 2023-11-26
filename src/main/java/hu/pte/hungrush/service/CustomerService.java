package hu.pte.hungrush.service;

import hu.pte.hungrush.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.CustomerRepo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
@Transactional
public class CustomerService {
    
    @Autowired
    private CustomerRepo repo;
    
        @PersistenceContext
    private EntityManager em;
    
        // Get all customers
        public List<Customer> getCustomersJPA() {
        return repo.findAll();
        }
        
        // Get customer by ID
    public Customer getCustomer(Integer id) {
        return repo.findById(id).get();
    }
        // Create a customer
    public void addCustomer(Customer customer) {
        repo.save(customer);
    }
    
        //Delete a customer
    public void deleteCustomer(Integer id) {
        repo.deleteById(id);
    }
    
        // Get Customers SPQ
    public List<Customer> getAllCustomersSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCustomers");
        return spq.getResultList();
    }
    
        // Add Customers SPQ
    public void addCustomerSPQ(Customer customer) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addCustomer");
        
        spq.registerStoredProcedureParameter("addressIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("phonenumberIN", String.class, ParameterMode.IN);
        
        //set values for each parameter
        spq.setParameter("addressIN", customer.getAddress());
        spq.setParameter("emailIN", customer.getEmail());
        spq.setParameter("nameIN", customer.getName());
        spq.setParameter("phonenumberIN", customer.getPhoneNumber());
        
        spq.execute();
    }
    
    
        public void deleteCustomerSPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCustomer");
        
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);
        
        spq.execute();
        
    }
}
