package hu.pte.hungrush.controller;

import hu.pte.hungrush.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourierController {
    @Autowired
    private CourierService service;
}
