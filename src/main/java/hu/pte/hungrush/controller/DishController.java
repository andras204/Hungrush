package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
