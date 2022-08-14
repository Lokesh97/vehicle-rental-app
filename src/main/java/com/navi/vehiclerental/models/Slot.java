package com.navi.vehiclerental.models;

public class Slot {
    private Integer startTime;
    private Integer endTime;

    public Slot(Integer startTime, Integer endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Boolean isOverlapping(Slot slot){
        if(this.startTime >= slot.getEndTime() || this.endTime <= slot.getStartTime()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }
}
