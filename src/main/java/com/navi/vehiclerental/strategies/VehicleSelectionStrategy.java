package com.navi.vehiclerental.strategies;

import com.navi.vehiclerental.models.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("vehicleSelectionStrategy")
public interface VehicleSelectionStrategy {
    public void getVehicleSelectionOrder(List<Vehicle> vehicles);
}
