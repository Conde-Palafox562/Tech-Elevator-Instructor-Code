package com.techelevator.controller;

import com.techelevator.dao.TruckDao;
import com.techelevator.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TruckController {

    @Autowired
    TruckDao truckDao;

    @RequestMapping(path="/trucks", method = RequestMethod.POST)
    public Truck createTruck(@RequestBody Truck truck){
        return truckDao.createTruck(truck);
    }
}
