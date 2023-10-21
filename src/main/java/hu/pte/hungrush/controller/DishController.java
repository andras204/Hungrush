package hu.pte.hungrush.controller;

import hu.pte.hungrush.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishController {
    @Autowired
    private DishService service;
}
