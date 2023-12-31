package hu.pte.hungrush.service;

import hu.pte.hungrush.model.Delivery;
import hu.pte.hungrush.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.DeliveryRepo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Tuple;

@Service
@Transactional
public class DeliveryService {
    @Autowired
    private DeliveryRepo repo;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Dish> getOrderedDishes(Integer oredrID) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("getOrderedDishes");
        query.registerStoredProcedureParameter("delivery_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("delivery_id_IN", oredrID);
        List<Object> results = query.getResultList();
        
        // get the dishes from IDs
        StoredProcedureQuery dish_decoder = em.createStoredProcedureQuery("getDish");
        dish_decoder.registerStoredProcedureParameter("dish_id_IN", Integer.class, ParameterMode.IN);
        List<Dish> dishes = new ArrayList<Dish>();
        
        for(int i = 0; i < results.size(); i++) {
            Object[] touple = (Object[]) results.get(i);
            dish_decoder.setParameter("dish_id_IN", (Integer) touple[0]);
            
            Object[] o = (Object[]) dish_decoder.getSingleResult();
            Dish d = new Dish(
                    (Integer)o[0],
                    (String)o[1],
                    (String)o[2],
                    (Integer)o[3],
                    (String)o[4]
            );
            
            for(int j = 0; j < (Integer) touple[1]; j++) {
                dishes.add(d);
            }
        }
        
        return dishes;
    }
    
        // Get all customers
        public List<Delivery> getDeliveriesJPA() {
        return repo.findAll();
        }
        
        // Get customer by ID
    public Delivery getDelivery(Integer id) {
        return repo.findById(id).get();
    }
        // Create a customer
    public void addDelivery(Delivery customer) {
        repo.save(customer);
    }
    
        //Delete a customer
    public void deleteDelivery(Integer id) {
        repo.deleteById(id);
    }
    
        // Get Deliverys SPQ
    public List<Delivery> getAllDeliverysSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllDeliverys");
        return spq.getResultList();
    }
    
        // Add Deliverys SPQ
    public void addDeliverySPQ(Delivery customer) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addDelivery");
        
        spq.registerStoredProcedureParameter("courieridIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("customeridIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("restaurantidIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIN", String.class, ParameterMode.IN);
        
        //set values for each parameter
        spq.setParameter("courieridIN", customer.getCourierId());
        spq.setParameter("customeridIN", customer.getCustomerId());
        spq.setParameter("restaurantidIN", customer.getRestaurantId());
        spq.setParameter("statusIN", customer.getStatus());
        
        spq.execute();
    }
    
    
        public void deleteDeliverySPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteDelivery");
        
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);
        
        spq.execute();
        
    }
}
