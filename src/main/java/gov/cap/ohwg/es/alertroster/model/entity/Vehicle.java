package gov.cap.ohwg.es.alertroster.model.entity;

/**
 * Created by ckovacs on 4/23/16.
 */
public class Vehicle {
    private String make;
    private String model;
    private int passengerCapacity;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
