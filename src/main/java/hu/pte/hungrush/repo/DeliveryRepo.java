package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Delivery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {
    

    // We need to make a one-to-many or one-to-one relationship with customer for this to work I think?
    @Query("SELECT d FROM Delivery d WHERE d.customerId = :customerId")
    List<Delivery> getDeliveriesByCustomerId(@Param("customerId") Long customerId);
}


