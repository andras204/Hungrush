package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Courier;
import hu.pte.hungrush.service.CourierService;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
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
public class CourierController {
    @Autowired
    private CourierService service;
        @Autowired
        
        
    // get all couriers in JPA
        
        @GetMapping("/courier/jpa")
    public List<Courier> getAllCouriers() {
        return service.getCouriersJPA();
    }
    

    // gett courier by ID in JPA
    
    @GetMapping(value="/courier/jpa/{id}")
    public ResponseEntity<Courier> getCourier(@PathVariable Integer id) {
        try {
            Courier courier = service.getCourier(id);
            return new ResponseEntity<Courier> (courier, HttpStatus.OK);
            
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Courier> (HttpStatus.NOT_FOUND);
        }
    }
    
    // create a new courier in JPA
    
    @PostMapping(value="/addCourier/jpa")
    public void addCourier(@RequestBody Courier c){
        service.addCourier(c);
    }
    
    // Delete a courier in JPA
    
    @DeleteMapping(value="/removeCourier/jpa/{id}")
    public void deleteCourier(@PathVariable Integer id) {
        service.deleteCourier(id);
    }    
    
    // Edit a courier in JPA
    
    @PutMapping(value="/updateCourier/jpa/{id}")
    public ResponseEntity<Courier> updateCourier(@RequestBody Courier courier, @PathVariable Integer id) {
        try {
            Courier existingCourier = service.getCourier(id);
            
            existingCourier.setName(courier.getName());
            existingCourier.setMeansOfTransport(courier.getMeansOfTransport());
            existingCourier.setPhoneNumber(courier.getPhoneNumber());
            existingCourier.setStatus(courier.getStatus());
            service.addCourier(existingCourier);
            
            return new ResponseEntity<> (HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    } 
    
    
    // ---SPQ---
    
    
    // Get all couriers Stored Procedure
    @GetMapping(value="/couriers/spq")
    public List<Courier> getAllCouriersSPQ() {
        return service.getAllCouriersSPQ();
    }
    

    // Add courier Stored Procedure
    @PostMapping(value="/addCourier/spq")
    public void addCourierSPQ(@RequestBody Courier courier) {
        service.addCourierSPQ(courier);
    }
    
    // Delete courier Stored Procedure
    @DeleteMapping(value="/removeCourier/spq/{id}")
    public void deleteCourierSPQ(@PathVariable Integer id) {
        service.deleteCourierSPQ(id);
    }
    
    @PutMapping(value="/updateCourier/spq/")
    public void updateCourierSPQ(@RequestBody Courier courier) {
        service.updateCourierSPQ(courier);
    }
    
        @GetMapping("/getIdleCouriers/spq")
    public List<Courier> getIdleCouriers() {
       return service.getIdleCouriers();
    }

    
}
