package models;

import java.sql.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Promotion)) {
            return false;
        }
        Promotion promotion = (Promotion) o;
        return promoNumber == promotion.promoNumber && Objects.equals(promoName, promotion.promoName) && Objects.equals(startDate, promotion.startDate) && Objects.equals(endDate, promotion.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promoNumber, promoName, startDate, endDate);
    }

}
