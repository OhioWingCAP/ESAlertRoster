package gov.cap.ohwg.es.alertroster.model.entity;

import java.util.Date;

/**
 * Created by ckovacs on 5/10/16.
 */
public class Member implements Identifiable{
    private long capid;
    private String ssn;
    private String nameLast;
    private String nameFirst;
    private String nameMiddle;
    private String nameSuffix;
    private String gender;
    private Date dob;
    private String profession;
    private String educationLevel;
    private String citizen;
    private Long orgid;
    private String wing;
    private String unit;
    private String rank;
    private Date joined;
    private Date expiration;
    private String orgJoined;
    private String usrID;
    private Date dateMod;
    private String lsCode;
    private String type;
    private String rankDate;
    private String region;
    private String mbrStatus;
    private String picStatus;
    private Date picDate;
    private String cdtWaiver;

    public Member() {
    }

    public long getCapid() {
        return capid;
    }

    public void setCapid(long capid) {
        this.capid = capid;
    }

    public String getCdtWaiver() {
        return cdtWaiver;
    }

    public void setCdtWaiver(String cdtWaiver) {
        this.cdtWaiver = cdtWaiver;
    }

    public String getCitizen() {
        return citizen;
    }

    public void setCitizen(String citizen) {
        this.citizen = citizen;
    }

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getLsCode() {
        return lsCode;
    }

    public void setLsCode(String lsCode) {
        this.lsCode = lsCode;
    }

    public String getMbrStatus() {
        return mbrStatus;
    }

    public void setMbrStatus(String mbrStatus) {
        this.mbrStatus = mbrStatus;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameMiddle() {
        return nameMiddle;
    }

    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getOrgJoined() {
        return orgJoined;
    }

    public void setOrgJoined(String orgJoined) {
        this.orgJoined = orgJoined;
    }

    public Date getPicDate() {
        return picDate;
    }

    public void setPicDate(Date picDate) {
        this.picDate = picDate;
    }

    public String getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(String picStatus) {
        this.picStatus = picStatus;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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
        return getCapid();
    }
}
