package gov.cap.ohwg.es.alertroster.model.entity;

/**
 * Created by ckovacs on 4/23/16.
 */
public class PhoneNumber {
    private String phoneNumber;
    private PhoneNumberType type;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public void setType(PhoneNumberType type) {
        this.type = type;
    }
}
