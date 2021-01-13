package com.entity;

public class PaiBan {
    private Integer id;

    private Integer fid;

    private Integer uid;

    private Integer yi;

    private Integer er;

    private Integer san;

    private Integer si;

    private Integer wu;

    private Integer liu;

    private Integer tian;

    private String pubtime;

    private String status;

    private String ptype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getYi() {
        return yi;
    }

    public void setYi(Integer yi) {
        this.yi = yi;
    }

    public Integer getEr() {
        return er;
    }

    public void setEr(Integer er) {
        this.er = er;
    }

    public Integer getSan() {
        return san;
    }

    public void setSan(Integer san) {
        this.san = san;
    }

    public Integer getSi() {
        return si;
    }

    public void setSi(Integer si) {
        this.si = si;
    }

    public Integer getWu() {
        return wu;
    }

    public void setWu(Integer wu) {
        this.wu = wu;
    }

    public Integer getLiu() {
        return liu;
    }

    public void setLiu(Integer liu) {
        this.liu = liu;
    }

    public Integer getTian() {
        return tian;
    }

    public void setTian(Integer tian) {
        this.tian = tian;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime == null ? null : pubtime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype == null ? null : ptype.trim();
    }
}