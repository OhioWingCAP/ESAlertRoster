package gov.cap.ohwg.es.alertroster.model.entity;

import java.beans.Transient;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ckovacs on 4/23/16.
 */
public class Unit implements Identifiable {
    private long orgid;
    private String region;
    private String wing;
    private String unit;
    private Long nextLevel;
    private String name;
    private String type;
    private Date dateChartered;
    private String status;
    private String scope;
    private String usrID;
    private Date dateMod;
    private String firstUsr;
    private Date dateCreated;
    private Date dateReceived;
    private String orgNotes;

    private List<Long> alertRosterCapids = new ArrayList<>();
    private Unit parent;

    public Unit() {
    }

    public String getCharter() {
        try {
            int intUnit = Integer.parseInt(unit);
            unit = String.format("%03d", intUnit);
        } catch (NumberFormatException e) {
            // This can be safely ignored
        }
        return String.format("%s-%s-%s", region, wing, unit);
    }

    public List<Long> getAlertRosterCapids() {
        return alertRosterCapids;
    }

    public void setAlertRosterCapids(List<Long> alertRosterCapids) {
        this.alertRosterCapids = alertRosterCapids;
    }

    public Date getDateChartered() {
        return dateChartered;
    }

    public void setDateChartered(Date dateChartered) {
        this.dateChartered = dateChartered;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getFirstUsr() {
        return firstUsr;
    }

    public void setFirstUsr(String firstUsr) {
        this.firstUsr = firstUsr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Long nextLevel) {
        this.nextLevel = nextLevel;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getOrgNotes() {
        return orgNotes;
    }

    public void setOrgNotes(String orgNotes) {
        this.orgNotes = orgNotes;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUsrID() {
        return usrID;
    }

    public void setUsrID(String usrID) {
        this.usrID = usrID;
    }

    public String getWing() {
        return wing;
    }

    public void setWing(String wing) {
        this.wing = wing;
    }

    @Override
    public Long getId() {
        return getOrgid();
    }

    public void setParent(Unit parent) {
        this.parent = parent;
    }

    @Transient
    public Unit getParent() {
        return parent;
    }
    
}
