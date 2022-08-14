package com.navi.vehiclerental.services;

import com.navi.vehiclerental.database.BookingManager;
import com.navi.vehiclerental.database.BranchManager;
import com.navi.vehiclerental.enums.VehicleType;
import com.navi.vehiclerental.models.Branch;
import com.navi.vehiclerental.models.Slot;
import com.navi.vehiclerental.models.Vehicle;
import com.navi.vehiclerental.strategies.VehicleSelectionStrategy;
import com.sun.source.tree.BreakTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("branchService")
public class BranchService {

    private static final String COMMA = ",";
    @Autowired
    private BranchManager branchManager;

    @Autowired
    private BookingManager bookingManager;

    @Autowired
    @Qualifier("minPriceVehicleSelectionStrategy")
    private VehicleSelectionStrategy displayVehicleStrategy;

    public Boolean addBranch(String branchName, String vehicleTypes){
        List<VehicleType> vehicleTypesList = this.getVehicleTypes(vehicleTypes);

        Branch branch = new Branch(branchName, vehicleTypesList);
        this.branchManager.addBranch(branch);
        return Boolean.TRUE;
    }

    public List<VehicleType> getVehicleTypes(String vehicleTypes){

        final List<VehicleType> vehicleTypesList = Arrays.stream(vehicleTypes.trim().split(COMMA))
                .map(String::trim)
                .filter(token -> (token.length() > 0))
                .map(VehicleType::valueOf)
                .collect(Collectors.toList());

        return vehicleTypesList;
    }

    public List<Vehicle> displayVehicles(String branchId, Integer startTime, Integer endTime){
        List<Vehicle> vehiclesInBranch = this.branchManager.getBranch(branchId).getVehicles();
        Slot slot = new Slot(startTime, endTime);
        List<Vehicle> vehiclesAvailable = new ArrayList<>();
        for(Vehicle vehicle:vehiclesInBranch){
            if(this.bookingManager.isSlotFree(vehicle.getId(), slot)){
                vehiclesAvailable.add(vehicle);
            }
        }

        this.displayVehicleStrategy.getVehicleSelectionOrder(vehiclesAvailable);
        return vehiclesAvailable;
    }

    public void setBranchManager(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    public void setBookingManager(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    public void setDisplayVehicleStrategy(VehicleSelectionStrategy displayVehicleStrategy) {
        this.displayVehicleStrategy = displayVehicleStrategy;
    }
}
