package models;

import java.util.Objects;

public class Store {
    int storeNumber;
    String storeName;
    float longitude;
    float latitude;
    String storeType;

    public Store() { }

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Store)) {
            return false;
        }
        Store store = (Store) o;
        return storeNumber == store.storeNumber && Objects.equals(storeName, store.storeName) && longitude == store.longitude && latitude == store.latitude && Objects.equals(storeType, store.storeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeNumber, storeName, longitude, latitude, storeType);
    }

}
