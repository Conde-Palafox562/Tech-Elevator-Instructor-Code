package com.techelevator.dao;

import com.techelevator.model.Truck;

import java.util.List;

public interface TruckDao {

    public Truck createTruck(Truck truck);
    public Truck getTruck(int id);
    public List<Truck> getTrucks();
    public void updateTruck(Truck truck);
}
