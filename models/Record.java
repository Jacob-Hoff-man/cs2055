package models;

public class Record {
    int purchaseId;
    int storeNumber;
    int coffeeId;
    int quantity;
    float purchasedPortion;
    float redeemedPortion;

    public Record() { }

    public int getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getStoreNumber() {
        return this.storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public int getCoffeeId() {
        return this.coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPurchasedPortion() {
        return this.purchasedPortion;
    }

    public void setPurchasedPortion(float purchasedPortion) {
        this.purchasedPortion = purchasedPortion;
    }

    public float getRedeemedPortion() {
        return this.redeemedPortion;
    }

    public void setRedeemedPortion(float redeemedPortion) {
        this.redeemedPortion = redeemedPortion;
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseId='" + getPurchaseId() + "'" +
            ", storeNumber='" + getStoreNumber() + "'" +
            ", coffeeId='" + getCoffeeId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", purchasedPortion='" + getPurchasedPortion() + "'" +
            ", redeemedPortion='" + getRedeemedPortion() + "'" +
            "}";
    }

}
