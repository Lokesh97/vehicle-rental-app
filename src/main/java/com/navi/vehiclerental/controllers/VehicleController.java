package com.navi.vehiclerental.controllers;

import com.navi.vehiclerental.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("vehicleController")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    public String addVehicle(String branchName, String vehicleType, String vehicleId, Integer price) {
        try {
            this.vehicleService.addVehicle(branchName, vehicleType, vehicleId, price);
            return String.valueOf(Boolean.TRUE);
        } catch (Exception exception) {
            //exception.printStackTrace();
            return String.valueOf(Boolean.FALSE);
        }
    }

}
