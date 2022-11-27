package models;

public class Store {
    int storeNumber;
    String storeName;
    float longitude;
    float latitude;
    String storeType;

    public Store() { }

    public Store(int storeNumber, String storeName, float longitude, float latitude, String storeType) {
        this.storeNumber = storeNumber;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    @Override
    public String toString() {
        return "Store { storeNumber=" + storeNumber + 
            ", storeName=" + storeName +
            ", longitude=" + longitude +
            ", latitude=" + latitude +
            ", storeType=" + storeType +
            " }\n";

    }

}
