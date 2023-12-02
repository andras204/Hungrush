package hu.pte.hungrush.service;

import hu.pte.hungrush.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.DishRepo;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class DishService {

    @Autowired
    private DishRepo repo;

    @PersistenceContext
    private EntityManager em;

    public Boolean isDishAvailableAtRestaurant(Integer dishID, Integer restaurantID) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("isDishAvailableAtRestaurant");
        query.registerStoredProcedureParameter("restaurant_id_IN", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dish_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("restaurant_id_IN", restaurantID);
        query.setParameter("dish_id_IN", dishID);
        int result = Integer.parseInt(query.getSingleResult().toString());
        return result >= 1;
    }

//    
//        public Dish getDish(Integer id) {
//        return repo.findById(id).get();
//    }
//    public Dish getDish(Integer id) throws Exception {
//        Optional<Dish> result = repo.findById(id);
//        if(result.isEmpty()) {
//            throw new Exception("no dish with id \"" + id + "\"");
//        }
//        return result.get();
//    }
    // Get all dishes
    public List<Dish> getDishesJPA() {
        return repo.findAll();
    }

    // Get dish by ID
    public Dish getDish(Integer id) {
        return repo.findById(id).get();
    }
    // Create a dish

    public void addDish(Dish dish) {
        repo.save(dish);
    }

    //Delete a dish
    public void deleteDish(Integer id) {
        repo.deleteById(id);
    }

    // ---------SPQ----------
    // Get Dishes SPQ
    public List<Dish> getAllDishesSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllDishes");
        return spq.getResultList();
    }

    // Add Dishes SPQ
    public void addDishSPQ(Dish dish) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addDish");

        spq.registerStoredProcedureParameter("CategoryIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("imageUrlIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("priceIN", String.class, ParameterMode.IN);

        //set values for each parameter
        spq.setParameter("categoryIN", dish.getCategory());
        spq.setParameter("imageUrlIN", dish.getImageUrl());
        spq.setParameter("nameIN", dish.getName());
        spq.setParameter("priceIN", dish.getPrice());

        spq.execute();
    }

    public void deleteDishSPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteDish");

        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);

        spq.execute();

    }
    // Update dish SPQ

    public void updateDishSPQ(Dish dish) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateDish");
        spq.registerStoredProcedureParameter("dishidIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("CategoryIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("imageUrlIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("priceIN", String.class, ParameterMode.IN);

        spq.setParameter("dishidIN", dish.getId());
        spq.setParameter("categoryIN", dish.getCategory());
        spq.setParameter("imageUrlIN", dish.getImageUrl());
        spq.setParameter("nameIN", dish.getName());
        spq.setParameter("priceIN", dish.getPrice());

        spq.execute();
    }

    public List<Dish> getDishesByCategory(String category) {
        List<Dish> result = repo.getDishesByCategory(category);
        if (CollectionUtils.isEmpty(result)) {
            throw new NoResultException();
        } else {
            return result;
        }
    }

    public List<Dish> sortDishesByPrice() {
        List<Dish> result = repo.sortDishesByPrice();
        if (CollectionUtils.isEmpty(result)) {
            throw new NoResultException();
        } else {
            return result;

        }
    }
}
