package hu.pte.hungrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.CustomerRepo;

@Service
@Transactional
public class CustomerService {
    
    @Autowired
    private CustomerRepo repo;
}
