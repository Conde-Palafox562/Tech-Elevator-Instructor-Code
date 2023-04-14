package com.techelevator.controller;

import com.techelevator.dao.TruckDao;
import com.techelevator.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TruckController {

    @Autowired
    TruckDao truckDao;

    @RequestMapping(path="/trucks", method = RequestMethod.POST)
    public Truck createTruck(@RequestBody Truck truck){
        return truckDao.createTruck(truck);
    }

    @RequestMapping(path="/trucks/{id}", method = RequestMethod.GET)
    public Truck getTruck(@PathVariable int id){
        return truckDao.getTruck(id);
    }

    @RequestMapping(path="/trucks", method=RequestMethod.GET)
    public List<Truck> getTrucks(){
        return truckDao.getTrucks();
    }
}
