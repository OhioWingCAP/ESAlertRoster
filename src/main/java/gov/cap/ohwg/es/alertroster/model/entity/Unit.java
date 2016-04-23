package gov.cap.ohwg.es.alertroster.model.entity;

import com.google.appengine.api.datastore.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckovacs on 4/23/16.
 */
public class Unit implements Identifiable {
    private String charter;
    private String name;
    private String parentCharter;

    private List<Person> alertRoster = new ArrayList<>();

    public Unit() {
    }

    public Unit(Entity entity) {
        this.charter = (String) entity.getProperty("charter");
        this.name = (String) entity.getProperty("name");
        this.parentCharter = (String) entity.getProperty("parentCharter");
    }

    public Unit(String charter, String name, String parentCharter) {
        this.charter = String.format("%03d", Integer.valueOf(charter));
        this.name = name;
        this.parentCharter = parentCharter;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getAlertRoster() {
        return alertRoster;
    }

    public void setAlertRoster(List<Person> alertRoster) {
        this.alertRoster = alertRoster;
    }

    public String getParentCharter() {
        return parentCharter;
    }

    public void setParentCharter(String parentCharter) {
        this.parentCharter = parentCharter;
    }

    @Override
    public String getId() {
        return charter;
    }
}
