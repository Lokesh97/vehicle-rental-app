package com.navi.vehiclerental.services;

import com.navi.vehiclerental.database.BookingManager;
import com.navi.vehiclerental.database.BranchManager;
import com.navi.vehiclerental.enums.VehicleType;
import com.navi.vehiclerental.exceptions.VehicleNotAvailaibleForSlotException;
import com.navi.vehiclerental.models.Slot;
import com.navi.vehiclerental.models.Vehicle;
import com.navi.vehiclerental.strategies.VehicleSelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller("bookingService")
public class BookingService {

    @Autowired
    private BranchManager branchManager;

    @Autowired
    @Qualifier("minPriceVehicleSelectionStrategy")
    private VehicleSelectionStrategy vehicleSelectionStrategy;

    @Autowired
    private BookingManager bookingManager;

    public Integer book(String branchId, String vehicleType, Integer startTime, Integer endTime) {

        List<Vehicle> vehiclesInBranch = this.branchManager.getBranch(branchId)
                                             .getVehiclesByType(VehicleType.valueOf(vehicleType));
        Slot slot = new Slot(startTime, endTime);
        List<Vehicle> vehiclesAvailable = new ArrayList<>();
        for(Vehicle vehicle:vehiclesInBranch){
            if(this.bookingManager.isSlotFree(vehicle.getId(), slot)){
                vehiclesAvailable.add(vehicle);
            }
        }

        this.vehicleSelectionStrategy.getVehicleSelectionOrder(vehiclesAvailable);

        for(Vehicle vehicle:vehiclesAvailable){
            if(this.bookingManager.bookSlot(vehicle.getId(),slot)){
                return vehicle.getPrice() * (endTime-startTime);
            }
        }

        throw new VehicleNotAvailaibleForSlotException();
    }

    public void setBranchManager(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    public void setVehicleSelectionStrategy(VehicleSelectionStrategy vehicleSelectionStrategy) {
        this.vehicleSelectionStrategy = vehicleSelectionStrategy;
    }

    public void setBookingManager(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }
}
