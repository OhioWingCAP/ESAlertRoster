package gov.cap.ohwg.es.alertroster.model.entity;

import java.util.Date;

/**
 * Created by ckovacs on 5/12/16.
 */
public class DutyPosition implements Identifiable {
    private long capid;
    private String duty;
    private String functArea;
    private String lvl;
    private long asst;
    private String usrId;
    private Date dateMod;
    private long orgid;

    public long getAsst() {
        return asst;
    }

    public void setAsst(long asst) {
        this.asst = asst;
    }

    public long getCapid() {
        return capid;
    }

    public void setCapid(long capid) {
        this.capid = capid;
    }

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getFunctArea() {
        return functArea;
    }

    public void setFunctArea(String functArea) {
        this.functArea = functArea;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    @Override
    public Long getId() {
        return IdUtil.generateHash(this);
    }
}
