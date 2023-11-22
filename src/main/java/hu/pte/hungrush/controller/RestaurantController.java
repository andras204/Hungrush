package hu.pte.hungrush.controller;

import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.model.Restaurant;
import hu.pte.hungrush.service.RestaurantService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
