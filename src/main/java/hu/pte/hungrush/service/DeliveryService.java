package hu.pte.hungrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.DeliveryRepo;

@Service
@Transactional
public class DeliveryService {
    @Autowired
    private DeliveryRepo repo;
}
