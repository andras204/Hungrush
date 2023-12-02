package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {

}
