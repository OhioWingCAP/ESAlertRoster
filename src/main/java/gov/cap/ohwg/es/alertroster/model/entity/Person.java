package gov.cap.ohwg.es.alertroster.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckovacs on 4/23/16.
 */
public class Person {
    private String lastName;
    private String firstName;
    private String grade;
    private String capid;
    private String charter;
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public String getCapid() {
        return capid;
    }

    public void setCapid(String capid) {
        this.capid = capid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }
}
