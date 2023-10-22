package hu.pte.hungrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.DishRepo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

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
}
