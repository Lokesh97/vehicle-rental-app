package com.navi.vehiclerental.services;

import com.navi.vehiclerental.database.BookingManager;
import com.navi.vehiclerental.database.BranchManager;
import com.navi.vehiclerental.database.VehicleManager;
import com.navi.vehiclerental.exceptions.BranchNotExistsException;
import com.navi.vehiclerental.exceptions.VehicleNotAvailaibleForSlotException;
import com.navi.vehiclerental.strategies.MinPriceVehicleSelectionStrategy;
import com.navi.vehiclerental.strategies.VehicleSelectionStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertEquals;

@Component("testBookingController")
public class TestVehicleService {

    @Autowired
    VehicleService vehicleService;

    @Before
    public void init(){
        BookingManager bookingManager = new BookingManager();
        BranchManager branchManager = new BranchManager();
        VehicleManager vehicleManager = new VehicleManager();
        VehicleSelectionStrategy vehicleSelectionStrategy = new MinPriceVehicleSelectionStrategy();

        BookingService bookingService = new BookingService();
        bookingService.setBookingManager(bookingManager);
        bookingService.setBranchManager(branchManager);
        bookingService.setVehicleSelectionStrategy(vehicleSelectionStrategy);

        BranchService branchService = new BranchService();
        branchService.setBranchManager(branchManager);
        branchService.setBookingManager(bookingManager);
        branchService.setDisplayVehicleStrategy(vehicleSelectionStrategy);

        this.vehicleService = new VehicleService();
        vehicleService.setVehicleManager(vehicleManager);
        vehicleService.setBranchManager(branchManager);

        branchService.addBranch("B1", "CAR,BIKE");
    }

    @Test
    public void testAddVehicle_success(){
        assertEquals(true, this.vehicleService.addVehicle("B1", "CAR", "V1", 2).booleanValue());
    }
}
