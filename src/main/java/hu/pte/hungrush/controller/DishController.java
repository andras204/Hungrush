package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.service.DishService;
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
public class DishController {
    @Autowired
    private DishService service;
    
    @GetMapping("/dish/{d_id}/availableAtRestaurant/{r_id}")
    public Boolean getAvailableDishes(@PathVariable Integer d_id, @PathVariable Integer r_id) {
        return service.isDishAvailableAtRestaurant(d_id, r_id);
    }
    
    
    @GetMapping("/dish/{id}")
    public Dish getDish(@PathVariable Integer id) throws Exception {
        return service.getDish(id);
    }
    
            @GetMapping("/dish/jpa")
    public List<Dish> getAllDishes() {
        return service.getDishesJPA();
    }
    

    // get dish by ID in JPA
    
//    @GetMapping(value="/dish/jpa/{id}")
//    public ResponseEntity<Dish> getDish(@PathVariable Integer id) {
//        try {
//            Dish dish = service.getDish(id);
//            return new ResponseEntity<Dish> (dish, HttpStatus.OK);
//            
//        } catch(NoSuchElementException e) {
//            return new ResponseEntity<Dish> (HttpStatus.NOT_FOUND);
//        }
//    }
    
    
    // create a new dish in JPA
    
    @PostMapping(value="/addDish/jpa")
    public void addDish(@RequestBody Dish c){
        service.addDish(c);
    }
    
    // Delete a dish in JPA
    
    @DeleteMapping(value="/removeDish/jpa/{id}")
    public void deleteDish(@PathVariable Integer id) {
        service.deleteDish(id);
    }    
    
    // Edit a dish in JPA
    
    @PutMapping(value="/editDish/jpa/{id}")
    public ResponseEntity<Dish> updateDish(@RequestBody Dish dish, @PathVariable Integer id) {
        try {
            Dish existingDish = service.getDish(id);

            existingDish.setCategory(dish.getCategory());
            existingDish.setImageUrl(dish.getImageUrl());
            existingDish.setName(dish.getName());
            existingDish.setPrice(dish.getPrice());
            service.addDish(existingDish);
            
            return new ResponseEntity<> (HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    } 
    
    // Get all dishes Stored Procedure
    @GetMapping(value="/dishes/spq")
    public List<Dish> getAllDishesSPQ() {
        return service.getAllDishesSPQ();
    }
    

    // Add dish Stored Procedure
    @PostMapping(value="/addDish/spq")
    public void addDishSPQ(@RequestBody Dish dish) {
        service.addDishSPQ(dish);
    }
    
    // Delete dish Stored Procedure
    @DeleteMapping(value="/removeDish/spq/{id}")
    public void deleteDishSPQ(@PathVariable Integer id) {
        service.deleteDishSPQ(id);
    }
    
    @PutMapping(value="/updateDish/spq/")
    public void updateDishSPQ(@RequestBody Dish dish) {
        service.updateDishSPQ(dish);
    }
    
    
    @PostMapping(value="/getDishByCategory/{category}")
    public List<Dish>getDishesByCategory(@PathVariable String category) {
        return service.getDishesByCategory(category);
    }
}
