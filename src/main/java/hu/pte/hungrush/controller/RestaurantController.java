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
    
    @GetMapping("/restaurant/{r_id}/dishAvailable/{d_id}")
    public Boolean getAvailableDishes(@PathVariable Integer d_id, @PathVariable Integer r_id) {
        return service.isDishAvailableAtRestaurant(d_id, r_id);
    }
}
