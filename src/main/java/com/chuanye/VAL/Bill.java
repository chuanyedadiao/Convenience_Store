package com.chuanye.VAL;

public class Bill {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String commName;
    float commPrice;
    int commCount;

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public float getCommPrice() {
        return commPrice;
    }

    public void setCommPrice(float commPrice) {
        this.commPrice = commPrice;
    }

    public int getCommCount() {
        return commCount;
    }

    public void setCommCount(int commCount) {
        this.commCount = commCount;
    }
}
