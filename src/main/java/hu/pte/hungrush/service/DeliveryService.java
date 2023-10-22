package hu.pte.hungrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.DeliveryRepo;
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
    
    public List<Tuple> getOrderedDishes(Integer oredrID) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("getOrderedDishes");
        query.registerStoredProcedureParameter("delivery_id_IN", Integer.class, ParameterMode.IN);
        query.setParameter("delivery_id_IN", oredrID);
        return query.getResultList();
    }
}
