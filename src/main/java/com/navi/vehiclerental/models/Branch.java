package com.navi.vehiclerental.models;

import com.navi.vehiclerental.enums.VehicleType;
import com.navi.vehiclerental.exceptions.VehicleTypeNotSupportedAtBranchException;

import java.util.*;

public class Branch {
    private String id;
    private Map<VehicleType, List<Vehicle>> vehicles;

    public Branch(String name, List<VehicleType> vehicleTypes) {
        this.id = name;
        this.vehicles = new HashMap<>();
        for(VehicleType vehicleType: vehicleTypes){
            this.vehicles.put(vehicleType, new ArrayList<>());
        }
    }

    public String getId() {
        return id;
    }

    public void addVehicleByType(VehicleType vehicleType, Vehicle vehicle){
        if(!vehicles.containsKey(vehicleType)){
            throw new VehicleTypeNotSupportedAtBranchException();
        }
        this.vehicles.get(vehicleType).add(vehicle);
    }

    public List<Vehicle> getVehiclesByType(VehicleType vehicleType){
        if(!vehicles.containsKey(vehicleType)){
            throw new VehicleTypeNotSupportedAtBranchException();
        }
        return vehicles.get(vehicleType);
    }

    public List<Vehicle> getVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        for (VehicleType vehicleType: this.vehicles.keySet()){
            vehicles.addAll(this.vehicles.get(vehicleType));
        }

        return vehicles;
    }
}
