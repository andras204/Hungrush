package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Courier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CourierRepo extends JpaRepository<Courier, Integer> {
    @Query(value="SELECT c FROM Courier c WHERE c.id = :id")
    public List<Courier> findCourierByID(@Param("id")Integer id);
}
