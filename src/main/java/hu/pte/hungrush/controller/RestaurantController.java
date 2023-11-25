package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.model.Restaurant;
import hu.pte.hungrush.service.RestaurantService;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService service;
    
    @Autowired
    private EntityManager entityManager;
    
    @GetMapping("/restaurant/{id}/availableDishes")
    public List<Dish> getAvailableDishes(@PathVariable Integer id) {
        return service.getAvailableDishes(id);
    }
    
    @GetMapping("/restaurant/{r_id}/dishAvailable/{d_id}")
    public Boolean getAvailableDishes(@PathVariable Integer d_id, @PathVariable Integer r_id) {
        return service.isDishAvailableAtRestaurant(d_id, r_id);
    }
    
        @GetMapping("/restaurant/{r_id}")
    public Boolean isRestaurantOpen(@PathVariable Integer r_id) {
        return service.isRestaurantOpen(r_id);
    }
        @Transactional
    public List<Restaurant> listRestaurantCategory() {
        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
        return query.getResultList();
    }
    
            @GetMapping("/restaurant/jpa")
    public List<Restaurant> getAllRestaurants() {
        return service.getRestaurantsJPA();
    }
    

    // gett restaurant by ID in JPA
    
    @GetMapping(value="/restaurant/jpa/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        try {
            Restaurant restaurant = service.getRestaurant(id);
            return new ResponseEntity<Restaurant> (restaurant, HttpStatus.OK);
            
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Restaurant> (HttpStatus.NOT_FOUND);
        }
    }
    
    // create a new restaurant in JPA
    
    @PostMapping(value="/addRestaurant/jpa")
    public void addRestaurant(@RequestBody Restaurant c){
        service.addRestaurant(c);
    }
    
    // Delete a restaurant in JPA
    
    @DeleteMapping(value="/removeRestaurant/jpa/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        service.deleteRestaurant(id);
    }    
    
    // Edit a restaurant in JPA
    
    @PutMapping(value="/editRestaurant/jpa/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Integer id) {
        try {
            Restaurant existingRestaurant = service.getRestaurant(id);
            
            existingRestaurant.setCategory(restaurant.getCategory());
            existingRestaurant.setAddress(restaurant.getAddress());
            existingRestaurant.setClosingTime(restaurant.getClosingTime());
            existingRestaurant.setOpeningTime(restaurant.getOpeningTime());
            existingRestaurant.setName(restaurant.getName());
            service.addRestaurant(existingRestaurant);
            
            
            return new ResponseEntity<> (HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    } 
    
    // Get all restaurants Stored Procedure
    @GetMapping(value="/restaurants/spq")
    public List<Restaurant> getAllRestaurantsSPQ() {
        return service.getAllRestaurantsSPQ();
    }
    

    // Add restaurant Stored Procedure
    @PostMapping(value="/addRestaurant/spq")
    public void addRestaurantSPQ(@RequestBody Restaurant restaurant) {
        service.addRestaurantSPQ(restaurant);
    }
    
    // Delete restaurant Stored Procedure
    @DeleteMapping(value="/removeRestaurant/spq/{id}")
    public void deleteRestaurantSPQ(@PathVariable Integer id) {
        service.deleteRestaurantSPQ(id);
    }
    
}
