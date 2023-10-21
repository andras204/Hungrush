package hu.pte.hungrush.controller;

import hu.pte.hungrush.service.RestaurantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService service;
    
    @GetMapping("/restaurant/{id}/availableDishes")
    public List<Integer> getAvailableDishes(@PathVariable Integer id) {
        return service.getAvailableDishIDs(id);
    }
}
