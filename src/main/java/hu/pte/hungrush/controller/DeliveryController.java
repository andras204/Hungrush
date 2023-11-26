package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Delivery;
import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.service.DeliveryService;
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
public class DeliveryController {
    @Autowired
    private DeliveryService service;
    
    @GetMapping("/delivery/{id}/orderedDishes")
    public List<Dish> getOrderedDishes(@PathVariable Integer id) {
        return service.getOrderedDishes(id);
    }
    
            @GetMapping("/delivery/jpa")
    public List<Delivery> getAllDeliveries() {
        return service.getDeliveriesJPA();
    }
    

    // gett delivery by ID in JPA
    
    @GetMapping(value="/delivery/jpa/{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable Integer id) {
        try {
            Delivery delivery = service.getDelivery(id);
            return new ResponseEntity<Delivery> (delivery, HttpStatus.OK);
            
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Delivery> (HttpStatus.NOT_FOUND);
        }
    }
    
    // create a new delivery in JPA
    
    @PostMapping(value="/addDelivery/jpa")
    public void addDelivery(@RequestBody Delivery c){
        service.addDelivery(c);
    }
    
    // Delete a delivery in JPA
    
    @DeleteMapping(value="/removeDelivery/jpa/{id}")
    public void deleteDelivery(@PathVariable Integer id) {
        service.deleteDelivery(id);
    }    
    
    // Edit a delivery in JPA
    
    @PutMapping(value="/editDelivery/jpa/{id}")
    public ResponseEntity<Delivery> updateDelivery(@RequestBody Delivery delivery, @PathVariable Integer id) {
        try {
            Delivery existingDelivery = service.getDelivery(id);
            
            existingDelivery.setId(delivery.getRestaurantId());
            existingDelivery.setId(delivery.getCourierId());
            existingDelivery.setId(delivery.getCustomerId());
            existingDelivery.setStatus(delivery.getStatus());
            service.addDelivery(existingDelivery);
            
            return new ResponseEntity<> (HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    } 
    
    // Get all deliveries Stored Procedure
    @GetMapping(value="/deliverys/spq")
    public List<Delivery> getAllDeliverysSPQ() {
        return service.getAllDeliverysSPQ();
    }
    

    // Add delivery Stored Procedure
    @PostMapping(value="/addDelivery/spq")
    public void addDeliverySPQ(@RequestBody Delivery delivery) {
        service.addDeliverySPQ(delivery);
    }
    
    // Delete delivery Stored Procedure
    @DeleteMapping(value="/removeDelivery/spq/{id}")
    public void deleteDeliverySPQ(@PathVariable Integer id) {
        service.deleteDeliverySPQ(id);
    }
    
    
}
