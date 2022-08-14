package com.navi.vehiclerental.services;

import com.navi.vehiclerental.database.BranchManager;
import com.navi.vehiclerental.database.VehicleManager;
import com.navi.vehiclerental.enums.VehicleType;
import com.navi.vehiclerental.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("vehicleService")
public class VehicleService {
    @Autowired
    private VehicleManager vehicleManager;
    @Autowired
    private BranchManager branchManager;

    public Boolean addVehicle(String branchName, String vehicleType, String vehicleId, Integer price) {
        Vehicle vehicle = new Vehicle(vehicleId, VehicleType.valueOf(vehicleType), price);

        this.branchManager.getBranch(branchName).addVehicleByType(VehicleType.valueOf(vehicleType), vehicle);
        this.vehicleManager.addVehicle(vehicle);
        return Boolean.TRUE;
    }

    public void setVehicleManager(VehicleManager vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    public void setBranchManager(BranchManager branchManager) {
        this.branchManager = branchManager;
    }
}