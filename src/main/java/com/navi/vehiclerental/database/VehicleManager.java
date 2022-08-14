package com.navi.vehiclerental.database;

import com.navi.vehiclerental.exceptions.VehicleIdAlreadyExistsException;
import com.navi.vehiclerental.exceptions.VehicleIdNotExistsException;
import com.navi.vehiclerental.models.Vehicle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("vehicleManager")
public class VehicleManager {

    private Map<String, Vehicle> vehicleMap;

    public VehicleManager() {
        this.vehicleMap = new HashMap<>();
    }

    public Vehicle getVehicle(String id){
        if(!this.vehicleMap.containsKey(id)){
            throw new VehicleIdNotExistsException();
        }
        return vehicleMap.get(id);
    }
    public synchronized Boolean addVehicle(Vehicle vehicle){
        if(this.vehicleMap.containsKey(vehicle.getId())){
            throw new VehicleIdAlreadyExistsException();
        }
        this.vehicleMap.put(vehicle.getId(), vehicle);
        return Boolean.TRUE;
    }

    public synchronized Boolean removeVehicle(String id){
        if(!this.vehicleMap.containsKey(id)){
            throw new VehicleIdNotExistsException();
        }
        this.vehicleMap.remove(id);
        return Boolean.TRUE;
    }
}
