package com.techelevator.dao;

import com.techelevator.model.Truck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTruckDao implements TruckDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTruckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Truck createTruck(Truck truck) {

        //Step 1 - Create our return type
        Truck newTruck = null;

        //Step 2 - Create our sql
        String sql = "INSERT INTO trucks(active, accepted_payment, cuisine_id, restaurant_link, phone, current_location)\n" +
                "VALUES(?, ?, ?, ?, ?, ?) RETURNING truck_id;";

        //Step 3 - Send our sql to the database
        int truckId = jdbcTemplate.queryForObject(sql, int.class, truck.isActive(), truck.getAcceptedPayment(), truck.getCuisineId(),
                truck.getRestaurantLink(), truck.getPhone(), truck.getCurrentLocation());

        //Step 4 - do any conversions with our results if necessary
        newTruck = getTruck(truckId);

        //Step 5 - return our result
        return newTruck;
    }

    public Truck getTruck(int id) {

        //Step 1 - Create our return type
        Truck truck = null;

        //Step 2 - write our sql
        String sql = "SELECT truck_id, active, accepted_payment, cuisine_id, restaurant_link, phone, current_location \n" +
                "FROM trucks\n" +
                "WHERE truck_id = ?;";

        //Step 3 - send the sql to our database
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        //Step 4 - convert results
        while(results.next()) {
            truck = convertRowToTruck(results);
        }

        //Step 5 - return results
        return truck;
    }

    public List<Truck> getTrucks() {

        //Step 1 - Create our return type
        List<Truck> trucks = new ArrayList<>();

        //Step 2 - write our sql
        String sql = "SELECT truck_id, active, accepted_payment, cuisine_id, restaurant_link, phone, current_location \n" +
                "FROM trucks;";

        //Step 3 - send the sql to our database
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        //Step 4 - convert results
        while(results.next()) {
            Truck truck = convertRowToTruck(results);
            trucks.add(truck);
        }

        //Step 5 - return results
        return trucks;
    }

    @Override
    public void updateTruck(Truck truck) {
        String sql = "UPDATE trucks\n" +
                "SET active=?,\n" +
                "    accepted_payment = ?,\n" +
                "\tcuisine_id = ?,\n" +
                "\trestaurant_link = ?,\n" +
                "\tphone = ?,\n" +
                "\tcurrent_location = ?\n" +
                "WHERE truck_id = ?;\n";

        jdbcTemplate.update(sql, truck.isActive(), truck.getAcceptedPayment(), truck.getCuisineId(),
                truck.getRestaurantLink(), truck.getPhone(), truck.getCurrentLocation(), truck.getTruckId());
    }

    private Truck convertRowToTruck(SqlRowSet results){
        Truck truck = new Truck();
        truck.setActive( results.getBoolean("active") );
        truck.setTruckId( results.getInt("truck_id"));
        truck.setAcceptedPayment( results.getString("accepted_payment"));
        truck.setCuisineId(results.getInt("cuisine_id"));
        truck.setRestaurantLink(results.getString("restaurant_link"));
        truck.setPhone( results.getString("phone"));
        truck.setCurrentLocation( results.getString("current_location"));

        return truck;
    }
}
