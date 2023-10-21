package hu.pte.hungrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.CourierRepo;

@Service
@Transactional
public class CourierService {
    
    @Autowired
    private CourierRepo repo;
}
