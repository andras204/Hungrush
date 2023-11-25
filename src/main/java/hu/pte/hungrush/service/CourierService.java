package hu.pte.hungrush.service;

import hu.pte.hungrush.model.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.CourierRepo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
@Transactional
public class CourierService {
    
    @Autowired
    private CourierRepo repo;
    
    @PersistenceContext
    private EntityManager em;
    
        // Get all couriers
        public List<Courier> getCouriersJPA() {
        return repo.findAll();
        }
        
        // Get courier by ID
    public Courier getCourier(Integer id) {
        return repo.findById(id).get();
    }
        // Create a courier
    public void addCourier(Courier courier) {
        repo.save(courier);
    }
    
        //Delete a courier
    public void deleteCourier(Integer id) {
        repo.deleteById(id);
    }
    
        // Get Couriers SPQ
    public List<Courier> getAllCouriersSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCouriers");
        return spq.getResultList();
    }
    
        // Add Couriers SPQ
    public void addCourierSPQ(Courier courier) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addCourier");
        
        spq.registerStoredProcedureParameter("meansoftransportIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("phonenumberIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIN", String.class, ParameterMode.IN);
        
        //set values for each parameter
        spq.setParameter("meansoftransportIN", courier.getMeansOfTransport());
        spq.setParameter("nameIN", courier.getName());
        spq.setParameter("phonenumberIN", courier.getPhoneNumber());
        spq.setParameter("statusIN", courier.getStatus());
        
        spq.execute();
    }
    
    
        public void deleteCourierSPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCourier");
        
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);
        
        spq.execute();
        
    }
}
