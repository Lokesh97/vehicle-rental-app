package com.navi.vehiclerental.strategies;

import com.navi.vehiclerental.models.Vehicle;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("minPriceVehicleSelectionStrategy")
public class MinPriceVehicleSelectionStrategy implements VehicleSelectionStrategy {
    @Override
    public void getVehicleSelectionOrder(List<Vehicle> vehicles) {
        Collections.sort(vehicles, (v1, v2) -> (v1.getPrice() - v2.getPrice()));
    }
}
