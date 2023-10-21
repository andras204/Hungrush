package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {
    
}
