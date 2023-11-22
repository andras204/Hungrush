package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.service.DeliveryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {
    @Autowired
    private DeliveryService service;
    
    @GetMapping("/delivery/{id}/orderedDishes")
    public List<Dish> getOrderedDishes(@PathVariable Integer id) {
        return service.getOrderedDishes(id);
    }
}
