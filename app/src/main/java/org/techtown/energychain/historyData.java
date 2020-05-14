package org.techtown.energychain;

import java.io.Serializable;

public class historyData implements Serializable {
    private String cellerid;
    private String location;
    private String recipientid;
    private float transferAmount;
    private String second;

    public String getCellerid() {
        return cellerid;
    }

    public void setCellerid(String cellerid) {
        this.cellerid = cellerid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecipientid() {
        return recipientid;
    }

    public void setRecipientid(String recipientid) {
        this.recipientid = recipientid;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }


    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

}
