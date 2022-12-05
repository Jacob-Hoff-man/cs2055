package models;

import java.sql.Timestamp;
import java.util.List;

public class Sale {
    int purchaseId;
    int customerId;
    Timestamp purchasedTime;

    List<Record> records;
    
    public Sale() { }

    public List<Record> getRecords() {
        return this.records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public int getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getPurchasedTime() {
        return this.purchasedTime;
    }

    public void setPurchasedTime(Timestamp purchasedTime) {
        this.purchasedTime = purchasedTime;
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseId='" + getPurchaseId() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", purchasedTime='" + getPurchasedTime() + "'" +
            "}";
    }
}