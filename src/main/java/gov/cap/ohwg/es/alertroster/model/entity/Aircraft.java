package gov.cap.ohwg.es.alertroster.model.entity;

/**
 * Created by ckovacs on 4/23/16.
 */
public class Aircraft {
    private String tailNumber;
    private AircraftType type;
    private Unit homeUnit;
    private String airportCode;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Unit getHomeUnit() {
        return homeUnit;
    }

    public void setHomeUnit(Unit homeUnit) {
        this.homeUnit = homeUnit;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public AircraftType getType() {
        return type;
    }

    public void setType(AircraftType type) {
        this.type = type;
    }
}
