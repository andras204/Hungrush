package hu.pte.hungrush.service;

import hu.pte.hungrush.model.Dish;
import hu.pte.hungrush.model.Restaurant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.RestaurantRepo;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
@Transactional
public class RestaurantService {
    
    @Autowired
    private RestaurantRepo repo;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Dish> getAvailableDishes(Integer restaurantID) {
        // get list of dish IDs
        StoredProcedureQuery query = em.createStoredProcedureQuery("getAvailableDishes");
        query.registerStoredProcedureParameter("restaurant_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("restaurant_id_IN", restaurantID);
        List<Integer> ids = query.getResultList();
        
        // get the dishes from IDs
        StoredProcedureQuery dish_decoder = em.createStoredProcedureQuery("getDish");
        dish_decoder.registerStoredProcedureParameter("dish_id_IN", Integer.class, ParameterMode.IN);
        List<Dish> dishes = new ArrayList<Dish>();
        
        for(int i = 0; i < ids.size(); i++) {
            dish_decoder.setParameter("dish_id_IN", ids.get(i));
            Object[] o = (Object[]) dish_decoder.getSingleResult();
            Dish d = new Dish(
                    (Integer)o[0],
                    (String)o[1],
                    (String)o[2],
                    (Integer)o[3],
                    (String)o[4]
            );
            dishes.add(d);
        }
        
        return dishes;
    }
    
    public Boolean isDishAvailableAtRestaurant(Integer dishID, Integer restaurantID) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("isDishAvailableAtRestaurant");
        query.registerStoredProcedureParameter("restaurant_id_IN", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dish_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("restaurant_id_IN", restaurantID);
        query.setParameter("dish_id_IN", dishID);
        int result = Integer.parseInt(query.getSingleResult().toString());
        return result >= 1;
    }
                                                      //, Time openingTime, Time closingTime//
    public boolean isRestaurantOpen(Integer restaurantID) {
           StoredProcedureQuery query = em.createStoredProcedureQuery("isRestaurantOpen");
           query.registerStoredProcedureParameter("restaurant_id_IN", Integer.class, ParameterMode.IN);
           query.setParameter("restaurant_id_IN", restaurantID);
           int result = Integer.parseInt(query.getSingleResult().toString());
           return result >= 1;
//           LocalTime now = LocalTime.now();
//           LocalTime openTime = openingTime.toLocalTime();
//           LocalTime closeTime = closingTime.toLocalTime();
//           
//
//        return now.isAfter(openTime) || now.isBefore(closeTime);
}
    

}
