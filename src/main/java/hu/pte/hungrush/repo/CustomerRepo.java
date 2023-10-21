package hu.pte.hungrush.repo;

import hu.pte.hungrush.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    @Query(value="SELECT c FROM Customer c WHERE c.id = :id")
    public List<Customer> findCustomerByID(@Param("id")Integer id);
}
