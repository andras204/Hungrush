package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepo extends JpaRepository<Dish, Integer> {
    
}
