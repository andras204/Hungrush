package hu.pte.hungrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.pte.hungrush.repo.DishRepo;

@Service
@Transactional
public class DishService {
    @Autowired
    private DishRepo repo;
}
