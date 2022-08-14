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
public class TestBookingService {

    @Autowired
    BookingService bookingService;

    @Before
    public void init(){
        BookingManager bookingManager = new BookingManager();
        BranchManager branchManager = new BranchManager();
        VehicleManager vehicleManager = new VehicleManager();
        VehicleSelectionStrategy vehicleSelectionStrategy = new MinPriceVehicleSelectionStrategy();

        this.bookingService = new BookingService();
        bookingService.setBookingManager(bookingManager);
        bookingService.setBranchManager(branchManager);
        bookingService.setVehicleSelectionStrategy(vehicleSelectionStrategy);

        BranchService branchService = new BranchService();
        branchService.setBranchManager(branchManager);
        branchService.setBookingManager(bookingManager);
        branchService.setDisplayVehicleStrategy(vehicleSelectionStrategy);

        VehicleService vehicleService = new VehicleService();
        vehicleService.setVehicleManager(vehicleManager);
        vehicleService.setBranchManager(branchManager);

        branchService.addBranch("B1", "CAR,BIKE");
        vehicleService.addVehicle("B1", "CAR", "V1", 100);
    }

    @Test
    public void testBook_branchException() {
        Assert.assertThrows(BranchNotExistsException.class, ()->{
            this.bookingService.book("B2", "CAR", 1, 2);
        });
    }

    @Test
    public void testBook_vehicleException() {
        Assert.assertThrows(VehicleNotAvailaibleForSlotException.class, ()->{
            this.bookingService.book("B1", "BIKE", 1, 2);
        });
    }

    @Test
    public void testBook_success(){
        assertEquals(100, this.bookingService.book("B1", "CAR", 1, 2).intValue());
    }
}
