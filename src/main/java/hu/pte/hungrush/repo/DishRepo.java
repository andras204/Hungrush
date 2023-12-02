package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Dish;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DishRepo extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.category = :category")
    public List<Dish> getDishesByCategory(@Param("category") String category);

    @Query("SELECT d FROM Dish d ORDER BY CAST(d.price AS java.lang.Float) ASC")

    public List<Dish> sortDishesByPrice();

}
