package models;

import java.sql.Date;

public class Promotion {
    int promoNumber;
    String promoName;
    Date startDate;
    Date endDate;

    public Promotion() { }

    public int getPromoNumber() {
        return this.promoNumber;
    }

    public void setPromoNumber(int promoNumber) {
        this.promoNumber = promoNumber;
    }

    public String getPromoName() {
        return this.promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{" +
            " promoNumber='" + getPromoNumber() + "'" +
            ", promoName='" + getPromoName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }

}
