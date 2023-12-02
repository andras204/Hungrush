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

        for (int i = 0; i < ids.size(); i++) {
            dish_decoder.setParameter("dish_id_IN", ids.get(i));
            Object[] o = (Object[]) dish_decoder.getSingleResult();
            Dish d = new Dish(
                    (Integer) o[0],
                    (String) o[1],
                    (String) o[2],
                    (Integer) o[3],
                    (String) o[4]
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

    // Get a restaurant's category
    public String getRestaurantCategory(Integer restaurantID) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("getRestaurantCategory");
        query.registerStoredProcedureParameter("restaurant_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("restaurant_id_IN", restaurantID);

        Object categoryObject = query.getSingleResult();

        String category = (String) categoryObject;

        return category;
    }

    public List<Restaurant> getOpenRestaurants() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("getOpenRestaurants");

        return query.getResultList();
    }

    // Get all restaurants
    public List<Restaurant> getRestaurantsJPA() {
        return repo.findAll();
    }

    // Get restaurant by ID
    public Restaurant getRestaurant(Integer id) {
        return repo.findById(id).get();
    }
    // Create a restaurant

    public void addRestaurant(Restaurant restaurant) {
        repo.save(restaurant);
    }

    //Delete a restaurant
    public void deleteRestaurant(Integer id) {
        repo.deleteById(id);
    }

    
    // ---------SPQ----------
    
    
    // Get Restaurants SPQ
    public List<Restaurant> getAllRestaurantsSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRestaurants");
        return spq.getResultList();
    }

    // Add Restaurants SPQ
    public void addRestaurantSPQ(Restaurant restaurant) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addRestaurant");

        spq.registerStoredProcedureParameter("meansoftransportIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("phonenumberIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIN", String.class, ParameterMode.IN);

        //set values for each parameter
        spq.setParameter("addressIN", restaurant.getAddress());
        spq.setParameter("categoryIN", restaurant.getCategory());
        spq.setParameter("clostingtimeIN", restaurant.getClosingTime());
        spq.setParameter("openingtimeIN", restaurant.getOpeningTime());
        spq.setParameter("nameIN", restaurant.getName());

        spq.execute();
    }

    // Delete Restaurants SPQ
    public void deleteRestaurantSPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteRestaurant");

        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);

        spq.execute();

    }

    // Update Restaurant SPQ
    public void updateRestaurantSPQ(Restaurant restaurant) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateRestaurant");

        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("meansoftransportIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("phonenumberIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIN", String.class, ParameterMode.IN);

        spq.setParameter("idIN", restaurant.getId());
        spq.setParameter("addressIN", restaurant.getAddress());
        spq.setParameter("categoryIN", restaurant.getCategory());
        spq.setParameter("clostingtimeIN", restaurant.getClosingTime());
        spq.setParameter("openingtimeIN", restaurant.getOpeningTime());
        spq.setParameter("nameIN", restaurant.getName());

        spq.execute();
    }

}
