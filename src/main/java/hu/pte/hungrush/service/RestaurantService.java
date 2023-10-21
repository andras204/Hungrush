package hu.pte.hungrush.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.RestaurantRepo;

@Service
@Transactional
public class RestaurantService {
    
    @Autowired
    private RestaurantRepo repo;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Integer> getAvailableDishIDs(Integer restaurandID) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("getAvailableDishes");
        query.registerStoredProcedureParameter("restaurant_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("restaurant_id_IN", restaurandID);
        return query.getResultList();
    }
}
