package com.navi.vehiclerental.controllers;

import com.navi.vehiclerental.models.Vehicle;
import com.navi.vehiclerental.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("branchController")
public class BranchController {

    @Autowired
    BranchService branchService;

    public String addBranch(String branchName, String vehicleTypes){
        try {
            this.branchService.addBranch(branchName, vehicleTypes);
            return String.valueOf(Boolean.TRUE);
        }catch (Exception exception){
            exception.printStackTrace();
            return String.valueOf(Boolean.FALSE);
        }
    }

    public List<Vehicle> displayVehicles(String branchName, Integer startTime, Integer endTime){
        try {
            return this.branchService.displayVehicles(branchName, startTime, endTime);
        }catch (Exception exception){
            //exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
