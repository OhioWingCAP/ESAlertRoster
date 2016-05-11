package gov.cap.ohwg.es.alertroster.model;

import gov.cap.ohwg.es.alertroster.model.entity.Identifiable;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by ckovacs on 5/11/16.
 */
public class Contact implements Identifiable {
    private long capid;
    private String type;
    private String priority;
    private String contact;
    private String usrId;
    private Date dateMod;
    private Boolean doNotContact;

    public long getCapid() {
        return capid;
    }

    public void setCapid(long capid) {
        this.capid = capid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    public Boolean getDoNotContact() {
        return doNotContact;
    }

    public void setDoNotContact(Boolean doNotContact) {
        this.doNotContact = doNotContact;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    @Override
    public Long getId() {
        long hashCode = 1;
        for(Field f : Contact.class.getDeclaredFields()) {
            f.setAccessible(true);
            Object obj = null;
            try {
                obj = f.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }
}
