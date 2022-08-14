package com.navi.vehiclerental.models;

import com.navi.vehiclerental.enums.VehicleType;

public class Vehicle {
    private String id;
    private VehicleType vehicleType;
    private Integer price;

    public Vehicle(String id, VehicleType vehicleType, Integer price) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
