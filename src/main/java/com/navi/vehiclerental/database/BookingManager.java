package com.navi.vehiclerental.database;

import com.navi.vehiclerental.models.Slot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("bookingManager")
public class BookingManager {

    private Map<String, List<Slot>> bookedVehicleSlots;

    public BookingManager() {
        this.bookedVehicleSlots = new HashMap<>();
    }

    public synchronized Boolean bookSlot(String vehicleId, Slot slot){
        if(this.isSlotFree(vehicleId, slot)){
            if(this.bookedVehicleSlots.get(vehicleId)==null){
                this.bookedVehicleSlots.put(vehicleId, new ArrayList<>());
            }
            this.bookedVehicleSlots.get(vehicleId).add(slot);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public Boolean isSlotFree(String vehicleId, Slot slot){
        List<Slot> bookedSlots = this.bookedVehicleSlots.get(vehicleId);

        if(bookedSlots==null){
            return Boolean.TRUE;
        }

        for(Slot bookedSlot:bookedSlots){
            if(bookedSlot.isOverlapping(slot)){
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }
}
