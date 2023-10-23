package hu.pte.hungrush.service;

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
        
        // getting data out of a generic Object requires this cancer
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
            // man, java is fking garbage
        }
        
        return dishes;
    }
}
